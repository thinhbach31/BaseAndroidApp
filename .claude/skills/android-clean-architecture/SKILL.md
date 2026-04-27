---
name: android-clean-architecture
description: Scaffold or extend an Android Jetpack Compose project with layered clean architecture — multi-module Gradle (:app/:core/:domain/:data/:presentation), Hilt DI, Retrofit + OkHttp + Moshi networking, Room persistence, Coroutines + Flow, and Coil image loading. Trigger when the user asks to "set up clean architecture", "modularize this Android project", "add Hilt + Retrofit + Room scaffolding", create a base Android template, or reference this layered pattern. Do NOT trigger for iOS, web, KMP, or non-Android projects.
---

# Android Clean Architecture (Layered)

A reusable scaffold for production Android apps. Splits code into Gradle modules by **layer**, wired with Hilt. The `:domain` module is pure Kotlin and depends on nothing Android-specific, which makes it fast to build and trivial to unit test.

## When to use

**Use this skill when:**
- The user asks to set up, scaffold, or apply clean architecture to an Android project.
- The user asks to modularize an existing single-module Android app.
- The user mentions adding Hilt + Retrofit + Room together as a stack.
- The user wants a "base template" / "starter" for a new Android app.

**Do not use this skill when:**
- The project is iOS, Flutter, React Native, KMP, or web.
- The user only asks for one library (e.g. "just add Retrofit") — answer narrowly instead.
- The user wants feature-based modularization (`:feature:home`, `:feature:profile`). This skill is layer-based.

## Module graph

```
            ┌──────────────────────────────┐
            │            :app              │  Application class, MainActivity,
            │  @HiltAndroidApp, manifest   │  navigation host. Depends on all.
            └───────┬──────────┬───────────┘
                    │          │
                    ▼          ▼
            ┌──────────────┐  ┌──────────────┐
            │ :presentation│  │    :data     │
            │  Compose UI  │  │  Retrofit,   │
            │  ViewModels  │  │  Room, repos │
            └──────┬───────┘  └──────┬───────┘
                   │                 │
                   └────────┬────────┘
                            ▼
                    ┌──────────────┐
                    │   :domain    │   Pure Kotlin: entities,
                    │              │   repo interfaces, use cases.
                    └──────┬───────┘
                           │
                           ▼
                    ┌──────────────┐
                    │    :core     │   Shared utilities: Result,
                    │              │   dispatcher qualifiers.
                    └──────────────┘
```

**Dependency rules — enforce strictly:**
- `:domain` → depends on `:core` only. **No Android, no Hilt, no Retrofit, no Room.**
- `:data` → depends on `:domain`, `:core`. Implements interfaces from `:domain`.
- `:presentation` → depends on `:domain`, `:core`. Never depends on `:data`.
- `:app` → depends on `:presentation`, `:data`, `:core`. Wires the Hilt graph.

If you find yourself wanting `:presentation` to depend on `:data`, that's a smell — go through `:domain` interfaces.

## Per-module responsibilities

### `:domain` (pure Kotlin / `org.jetbrains.kotlin.jvm`)
- **Entities** — plain data classes (`User`, `Post`).
- **Repository interfaces** (`UsersRepository`).
- **Use cases** — single-responsibility classes with one `operator fun invoke(...)`.
- No Android imports. No Hilt annotations. No Retrofit/Room types.
- Use `kotlinx.coroutines` Flow only (the artifact is JVM-compatible).

### `:data` (`com.android.library`)
- **Repository implementations** (`UsersRepositoryImpl`).
- **Remote** — Retrofit interfaces, DTOs, mappers (`toDomain()`).
- **Local** — Room `@Entity`, `@Dao`, `@Database`.
- **DI** — `NetworkModule`, `DatabaseModule`, feature modules with `@Binds` for repo interfaces.

### `:presentation` (`com.android.library` + Compose)
- Compose screens, theming wrappers if any.
- `@HiltViewModel` ViewModels exposing `StateFlow<UiState>`.
- UI state classes (`UsersUiState`).
- Navigation graphs (Navigation-Compose).

### `:core` (`com.android.library` — kept Android-flavored so `:data`/`:presentation` can depend on it directly)
- `Result<T>` sealed wrapper or extension over `kotlin.Result`.
- Dispatcher qualifiers: `@IoDispatcher`, `@MainDispatcher`, `@DefaultDispatcher`.
- Common error types.
- Optional shared Compose components.

### `:app`
- `BaseAndroidApplication : Application` annotated `@HiltAndroidApp`.
- `MainActivity` annotated `@AndroidEntryPoint`.
- Top-level `NavHost`.
- Manifest with `android:name=".BaseAndroidApplication"`.

## Version catalog snippet

Add to `gradle/libs.versions.toml`. Versions below assume Kotlin 2.0+ (uses KSP for Hilt and Room).

```toml
[versions]
hilt = "2.52"
ksp = "2.2.10-2.0.2"            # MUST match Kotlin version
retrofit = "2.11.0"
okhttp = "4.12.0"
moshi = "1.15.1"
room = "2.6.1"
coroutines = "1.8.1"
coil = "2.7.0"
hiltNavigationCompose = "1.2.0"
lifecycleViewmodelCompose = "2.8.4"

[libraries]
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }
hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }

retrofit-core = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-converter-moshi = { group = "com.squareup.retrofit2", name = "converter-moshi", version.ref = "retrofit" }
okhttp-core = { group = "com.squareup.okhttp3", name = "okhttp", version.ref = "okhttp" }
okhttp-logging = { group = "com.squareup.okhttp3", name = "logging-interceptor", version.ref = "okhttp" }
moshi-core = { group = "com.squareup.moshi", name = "moshi", version.ref = "moshi" }
moshi-kotlin = { group = "com.squareup.moshi", name = "moshi-kotlin", version.ref = "moshi" }
moshi-codegen = { group = "com.squareup.moshi", name = "moshi-kotlin-codegen", version.ref = "moshi" }

room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }

kotlinx-coroutines-core = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version.ref = "coroutines" }
kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "coroutines" }

coil-compose = { group = "io.coil-kt", name = "coil-compose", version.ref = "coil" }

lifecycle-viewmodel-compose = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-compose", version.ref = "lifecycleViewmodelCompose" }

[plugins]
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
kotlin-jvm = { id = "org.jetbrains.kotlin.jvm", version.ref = "kotlin" }
android-library = { id = "com.android.library", version.ref = "agp" }
```

## Per-module `build.gradle.kts` skeletons

### `:domain` (pure Kotlin)
```kotlin
plugins { alias(libs.plugins.kotlin.jvm) }
java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}
dependencies {
  implementation(libs.kotlinx.coroutines.core)
  testImplementation(libs.junit)
}
```

### `:core` (Android library, no Compose)
```kotlin
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}
android { /* namespace, compileSdk, minSdk, compileOptions VERSION_11 */ }
dependencies {
  api(libs.kotlinx.coroutines.core)
  api(libs.kotlinx.coroutines.android)
}
```

### `:data` (Android library, KSP for Room + Hilt)
```kotlin
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}
android { /* same template */ }
dependencies {
  implementation(project(":domain"))
  implementation(project(":core"))

  implementation(libs.retrofit.core)
  implementation(libs.retrofit.converter.moshi)
  implementation(libs.okhttp.core)
  implementation(libs.okhttp.logging)
  implementation(libs.moshi.core)
  implementation(libs.moshi.kotlin)
  ksp(libs.moshi.codegen)

  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
```

### `:presentation` (Android library + Compose)
```kotlin
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}
android { /* + buildFeatures { compose = true } */ }
dependencies {
  implementation(project(":domain"))
  implementation(project(":core"))

  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.activity.compose)
  implementation(libs.lifecycle.viewmodel.compose)
  implementation(libs.hilt.navigation.compose)

  implementation(libs.coil.compose)

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
}
```

### `:app`
```kotlin
plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt)
}
dependencies {
  implementation(project(":presentation"))
  implementation(project(":data"))
  implementation(project(":core"))

  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  // Compose deps for MainActivity host
}
```

## Sample feature template (`Users`)

A minimal vertical slice showing the path through every layer. Use it as the copy-paste template when adding new features.

### `:domain`
```kotlin
// domain/users/User.kt
data class User(val id: Int, val name: String, val email: String)

// domain/users/UsersRepository.kt
interface UsersRepository {
  fun observeUsers(): Flow<List<User>>
  suspend fun refresh()
}

// domain/users/GetUsersUseCase.kt
class GetUsersUseCase @Inject constructor(  // OK: javax.inject.Inject is pure Java
  private val repository: UsersRepository
) {
  operator fun invoke(): Flow<List<User>> = repository.observeUsers()
}
```

> Note: `javax.inject.Inject` is a pure-Java annotation, allowed in `:domain`. Do **not** use `@HiltViewModel`, `@AndroidEntryPoint`, or any `dagger.hilt.*` annotation in `:domain`.

### `:data` — remote
```kotlin
// data/users/remote/UserDto.kt
@JsonClass(generateAdapter = true)
data class UserDto(val id: Int, val name: String, val email: String) {
  fun toDomain() = User(id, name, email)
}

// data/users/remote/UsersApi.kt
interface UsersApi {
  @GET("users") suspend fun getUsers(): List<UserDto>
}
```

### `:data` — local
```kotlin
// data/users/local/UserEntity.kt
@Entity(tableName = "users")
data class UserEntity(@PrimaryKey val id: Int, val name: String, val email: String) {
  fun toDomain() = User(id, name, email)
  companion object { fun fromDomain(u: User) = UserEntity(u.id, u.name, u.email) }
}

// data/users/local/UserDao.kt
@Dao
interface UserDao {
  @Query("SELECT * FROM users") fun observeAll(): Flow<List<UserEntity>>
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun upsertAll(users: List<UserEntity>)
}
```

### `:data` — repository impl
```kotlin
class UsersRepositoryImpl @Inject constructor(
  private val api: UsersApi,
  private val dao: UserDao,
  @IoDispatcher private val io: CoroutineDispatcher,
) : UsersRepository {
  override fun observeUsers(): Flow<List<User>> =
    dao.observeAll().map { list -> list.map { it.toDomain() } }
  override suspend fun refresh() = withContext(io) {
    val remote = api.getUsers()
    dao.upsertAll(remote.map { UserEntity(it.id, it.name, it.email) })
  }
}
```

### `:data` — DI
```kotlin
@Module @InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides @Singleton fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
  @Provides @Singleton fun okhttp(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
    .build()
  @Provides @Singleton fun retrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
  @Provides @Singleton fun usersApi(retrofit: Retrofit): UsersApi = retrofit.create()
}

@Module @InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides @Singleton fun db(@ApplicationContext ctx: Context): AppDatabase =
    Room.databaseBuilder(ctx, AppDatabase::class.java, "app.db").build()
  @Provides fun userDao(db: AppDatabase): UserDao = db.userDao()
}

@Module @InstallIn(SingletonComponent::class)
abstract class UsersDataModule {
  @Binds abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
```

### `:presentation`
```kotlin
data class UsersUiState(val users: List<User> = emptyList(), val loading: Boolean = false, val error: String? = null)

@HiltViewModel
class UsersViewModel @Inject constructor(
  getUsers: GetUsersUseCase,
  private val repository: UsersRepository,
) : ViewModel() {
  val state: StateFlow<UsersUiState> = getUsers()
    .map { UsersUiState(users = it) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UsersUiState(loading = true))

  init { viewModelScope.launch { runCatching { repository.refresh() } } }
}

@Composable
fun UsersScreen(vm: UsersViewModel = hiltViewModel()) {
  val state by vm.state.collectAsStateWithLifecycle()
  LazyColumn { items(state.users) { user -> Text("${user.name} — ${user.email}") } }
}
```

### `:core`
```kotlin
@Qualifier @Retention(AnnotationRetention.BINARY) annotation class IoDispatcher
@Qualifier @Retention(AnnotationRetention.BINARY) annotation class MainDispatcher
@Qualifier @Retention(AnnotationRetention.BINARY) annotation class DefaultDispatcher

@Module @InstallIn(SingletonComponent::class)
object DispatchersModule {
  @Provides @IoDispatcher fun io(): CoroutineDispatcher = Dispatchers.IO
  @Provides @MainDispatcher fun main(): CoroutineDispatcher = Dispatchers.Main
  @Provides @DefaultDispatcher fun default(): CoroutineDispatcher = Dispatchers.Default
}
```

### `:app`
```kotlin
@HiltAndroidApp
class BaseAndroidApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(s: Bundle?) { super.onCreate(s); setContent { AppTheme { UsersScreen() } } }
}
```
Manifest: `android:name=".BaseAndroidApplication"` and `<uses-permission android:name="android.permission.INTERNET"/>`.

## Naming conventions

- Package layout: `com.<app>.<layer>.<feature>` — e.g. `com.example.baseandroidapp.data.users.remote.UsersApi`.
- DTO suffix: `*Dto`. Entity suffix: `*Entity`. Domain models: bare names (`User`).
- Use case classes: `<Verb><Noun>UseCase` with `operator fun invoke()`.
- One Hilt module per concern: `NetworkModule`, `DatabaseModule`, `<Feature>DataModule`.

## Image loading with Coil

Use `AsyncImage(model = url, contentDescription = ...)` from `io.coil-kt:coil-compose`. Coil 2.x is Kotlin-first, Compose-idiomatic, and integrates with the Compose lifecycle out of the box. For more control over caching/loaders, inject a singleton `ImageLoader` from `:data` via Hilt and pass it to `AsyncImage(imageLoader = ...)` or set it as the application-wide loader by implementing `ImageLoaderFactory` on the Application class.

If the project later moves to Coil 3.x (multiplatform), swap `io.coil-kt:coil-compose` for `io.coil-kt.coil3:coil-compose` plus the explicit network artifact `io.coil-kt.coil3:coil-network-okhttp`.

## Verification checklist

After scaffolding, verify in this order:
1. `./gradlew :domain:test` — domain compiles as pure Kotlin (catches accidental Android imports).
2. `./gradlew :app:assembleDebug` — full graph builds, Hilt component compiles, no missing bindings.
3. Install + launch on emulator — sample screen loads from network and persists to Room.
4. Toggle airplane mode → relaunch → cached data still renders (proves repo wires DAO correctly).

## Pitfalls

- **`:domain` accidentally pulling Android**: if `./gradlew :domain:test` fails with `package android.* does not exist`, you imported something Android-flavored. Fix the import.
- **KSP version mismatch**: KSP version's prefix MUST match Kotlin version exactly (`2.2.10-2.0.2` for Kotlin 2.2.10). Pin in catalog.
- **Hilt missing binding**: when `assembleDebug` fails with "cannot be provided", check that the implementation class is `@Inject constructor(...)` AND has a `@Binds` mapping to its interface in a Hilt module.
- **Room compile-time error in `:data`**: ensure `ksp(libs.room.compiler)` (not `kapt`) is declared and the database class lists every entity in `entities = [...]`.
- **Compose ViewModel not injecting**: use `hiltViewModel()` from `androidx.hilt:hilt-navigation-compose`, not `viewModel()`.
