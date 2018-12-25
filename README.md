# PhotoSearchApp
An application that allows you to view a simple photo list using the Flickr API


## Video
![photoSearchgif](https://user-images.githubusercontent.com/46020020/50413076-d32ea980-084f-11e9-9c4f-e6ac0c54be81.gif)

## APK Download
[PhotoSearchApp.zip](https://github.com/PhotoSearchApp/PhotoSearchApp/files/2708116/PhotoSearchApp.zip)



## Dev Environment
Android Studio 3.2.0

MinSdk 15

TargetSdk 27

Kotlin 1.2.71


## Architecture Pattern
MVP 

Contract

Dependency Injection


## Use library
[Dagger](https://github.com/google/dagger)

[Retrofit](https://square.github.io/retrofit/)

[RxJava](https://github.com/ReactiveX/RxJava)

[Glide](https://github.com/bumptech/glide)

[Anko](https://github.com/Kotlin/anko)

[Realm](https://realm.io/docs/java/latest/)


## Use API
[Flickr Api Documents](https://www.flickr.com/services/api/flickr.photos.search.html)

If you want a AuthKey, use getString(R.string.authKey) in Code


## API Test
[test](https://www.flickr.com/services/api/explore/flickr.photos.search)

## Photo Url Mapping
[Photo Url Mapping Method](https://www.flickr.com/services/api/misc.urls.html)


## User scenarios

* Given: 사용자가 앱을 설치를 완료 상태에서
* When: 앱을 실행하면
* Then: 첫번쨰 키워드의 사진 리스트가 보여야 한다.
>
* Given: 키워드의 리스트가 보이는 상태에서
* When: 아래로 스크롤링을 하면
* Then: 무한 스크롤링이 되어야 한다.
>
* Given: 키워드의 리스트가 보이는 상태에서
* When: 다른 키워드를 선택하면
* Then: 선택한 키워드의 사진 리스트가 보여야 한다.
>
* Given: 다른 키워드를 선택하면
* When: 사진이 없을 경우
* Then: 결과 없음을 보여야 한다.
>
* Given: 네트워크가 활성화되지 않은 상태에서
* When: 키워드를 선택하면
* Then: 네트워크 연결이 필요하다는 Toast가 보여야 한다.


## 미처리 이슈
- 50X 응답에 대한 대응
- Test Code
- Crashlytics tracking
- Network logging-interceptor

## Related posts
[MVP post](https://medium.com/me/stories/public)

[Dagger post](https://medium.com/@dlgksah/dagger2-kotlin-example-4c90d3d56edc)

>
>



