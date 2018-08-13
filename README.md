# OkHttpCookieStorage
![Release](https://jitpack.io/v/Atreydos/OkHttpCookieStorage.svg)

An Android library that provide automatically management for Cookies inside OkHttpClient


## Implementation
**Step 1** Add the JitPack repository to your build file
Add it in your root **build.gradle** at the end of repositories:
```grovy
maven { url 'https://jitpack.io' }
```

**Step 2** Add the dependency
```grovy
dependencies {
     implementation 'com.github.atreydos:OkHttpCookieStorage:0.0.1'
}
```


## How to use
**Step 1** Init library in your **Application.class**
```java
CookieStorage.init(this);
```

**Step 2** Add interceptors to your **OkHttpClient**
Example of use:
```java
 OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .build();
```
**Step 3** Enjoy!

|Interceptor                   |Description                                             |
|------------------------------|--------------------------------------------------------|
|**ReceivedCookiesInterceptor**|`Catch and store new Cookies (from header "Set-Cookie")`|
|**AddCookiesInterceptor**     |`Adds stored Cookies to every request`                  |
