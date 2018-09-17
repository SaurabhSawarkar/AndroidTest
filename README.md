# AndroidTest

The app will be build in Kotlin, following MVVM design pattern.
Backend will use Retrofit and RxJava.

This app just make one single server call and displays data on UI, so there is very minimum scope to add j unit test cases for it.
However, it is possible to add integration and UI test cases for this app.

Token handling is not required in this app, so will be skipping interceptors and authenticators implementation in Retrofit.
If headers are required in the request then interceptors will be used.
Also, will try follow single responsibility principal for this app.