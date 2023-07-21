# :paperclip: 세종대학교 졸업프로젝트 캡스톤
> 개인 맞춤 유럽여행 서비스

![image](https://github.com/euics/euro-planner/assets/103410386/9ba79666-20fe-4dfe-af80-975981ff371c)

## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
  - [실행 화면](#4-실행-화면)   


- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)

- [발표 영상](#발표-영상)

## 들어가며
### 1. 프로젝트 소개

증가하는 유럽여행 수요 BUT 많은 불편한 점 존재
<br>
여행코스짜기: 32명 중 22명이 많은 여행지에서 어떤 여행지를 갈지 정하는데 많은 시간을 소요한다고 응답
<br>
동행/만남: 32명중 27명이 네이버 카페 '유랑'을 이용했지만, 네이버 카페의 특성상 소통의 어려움이 존재한다고 응답
<br><br>
구축한 AI 모델
https://github.com/euics/euro-planner-ai-model

### 2. 프로젝트 기능

- **사용자 -** Security 회원가입 및 로그인, JwtToken과 JwtTokenFilter를 통한 Authorization
- **소셜로그인 -** Google/Naver/Kakao 소셜로그인
- **동행 게시판 -** 게시글/댓글 CRUD 기능
- **Amadeus API 활용 -** Amadeus API를 활용한 Hotel List, Hotel Rate, Flight Offers

### 3. 사용 기술

#### 3-1 백엔드

##### 주요 프레임워크 / 라이브러리
- Java 11
- SpringBoot 2.7.10
- JPA(Spring Data JPA)
- Spring Security
- AWS RDS
- H2 DataBase
- AWS EC2
- Swagger2
- JwtToken
- Gson

##### Build Tool
- Gradle

##### DataBase
- H2Database
- AWS RDS

### 4. 실행 화면
  <details>
    <summary>메인 페이지</summary>   
       
    
  **1. 서비스 메인화면**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/27af9178-26cf-4515-96ac-fa8379f4f04e)
  <br>
  1. 동행버튼을 통해 여행메이트를 찾을 수 있도록 구현
  2. AI 경로 추천을 통해 내 취향의 여행지 추천받도록 구현
  3. 만남버튼을 통해 주변 여행객들과의 채팅 기능 구현
     
  </details>
  <br/>   


  <details>
    <summary>로그인 회원가입 관련</summary>   
       
    
  **1. 회원가입/로그인 항목**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/af69852e-9827-47ac-8069-42d506d16738)
  <br>
  로그인/회원가입 시 성별과 생년월일을 입력받아 비슷한 사람과의 동행/만남 필터링에 용이

  **2. 로그인**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/4618e7a5-36c6-4e40-baa5-7d5e5c7bb762)
     
  </details>
  <br/>   
  
 
  
   <details>
    <summary>AI 추천 관련</summary>   
       
  **1. 국가/여행 일수 선택**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/d86e916e-ee39-4cb0-b5e3-17d6a592a67c)
  <br>
  
  **2. 테마 선택**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/ae03ad22-fa64-4e1d-88cc-a09700a7ddf9)
  <br>
  
  **3. 선호 여행지 선택**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/52e9e9c1-ca9e-4f2b-affd-636f42ff5a3c)
  <br>

  **4. 여행 코스 선택**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/b058467b-820d-44be-9bb4-8130fdbfdc9b)
  <br>
  
  **5. 지도 화면**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/0a304304-9258-4b55-b134-fbe6dd83e732)
  <br>
  1. 음식점을 누르면 주변의 음식점 마커와 정보창 생성
  2. 내 관광지를 눌러서 정보 확인 가능
  3. 출발지와 도착지를 인기공항으로 설정
  4. 출발지와 도착지를 선택 후 구글맵 API를 활용한 최단경로 확인
  
  </details>
  <br/>   
 
   <details>
    <summary>동행 게시판/동행신청(채팅)</summary>   
       
  **1. 마이페이지 목록**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/f43cd657-1f65-4f63-b4dd-47c5b1214d5f)
  <br>
  1. 동행버튼을 누르면 게시판 화면 이동
  2. 세부 메뉴바를 통해 원하는 사람 필터링 가능
  3. 신청을 누르면 채팅화면으로 이동
           
  </details>
  <br/>
  
  <details>
    <summary>만남 기능/마이페이지</summary>   
       
  **1. 만남 기능/마이페이지**   
  ![image](https://github.com/euics/euro-planner/assets/103410386/e28e69de-602f-4990-9429-cd09b1329885)
  <br>
  1. 내 주변의 여행객 위치 확인
  2. 그 사람의 성향과 여행지를 확인 가능
  3. 채팅하기 버튼을 통해 채팅 가능
  4. 마이페이지에서 내 여행지 확인 가능
   
  </details>
  <br/>
   
## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  
<summary>패키지 구조 보기</summary>   
 

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂sejong
 ┃ ┃ ┃ ┗ 📂europlanner
 ┃ ┃ ┃ ┃ ┗ 📂component
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SwaggerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CorsConfig.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtTokenProvider.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜JwtTokenFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmadeusController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GoogleController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NaverController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserControler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommnetsDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FilghtOfferDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HotelListDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HotelRateDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂enumtype
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Gender.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂customexception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BadRequestException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UsernameExistException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomizedResponseEntityExceptionHandler.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂global
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseTimeEntity.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CountryRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.interface
 ┃ ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂serviceImpl
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmadeusServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GoogleServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NaverServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂serviceinterface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AmadeusService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentsService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GoogleService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NaverService.interface
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.interface
 ┃ ┃ ┃ ┃ ┃ ┣ 📂vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜RequestCreateBoard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜RequestUpdateBoard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂comments
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜RequestCreateComments.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜RequestUpdateComments.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜RequestLogin.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜RequestUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂amadues
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseFlightOffer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseHotelList.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜ResponseHotelRate.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseCreateBoard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseDeleteBoard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜ResponseGetBoard.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂comments
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseDeleteComments.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜ResponseGetComments.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜ExceptionResponse.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseGetUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseGoogleUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseKakaoUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseLogin.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┣ 📜ResponseNaverUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ┗ 📜ResponseUser.java
 ```
  
 </details>   
 <br/>    
   
     
 ### 2. DB 설계

![image](https://github.com/euics/euro-planner/assets/103410386/56016ada-167e-41cf-8983-4d74cce4e955)
   
<br/>

## 발표 영상
### 1. 시연
https://www.youtube.com/watch?v=xxAZ1-xFDMU&t=2s&ab_channel=%EC%84%B8%EC%A2%85%EB%8C%80%ED%95%99%EA%B5%90SW%EC%A4%91%EC%8B%AC%EB%8C%80%ED%95%99%EC%82%AC%EC%97%85%EB%8B%A8
