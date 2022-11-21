<div align="center">
  
<img width="476" alt="스크린샷 2022-11-21 오후 4 51 44" src="https://user-images.githubusercontent.com/81874493/202994403-2141bc71-076c-4a3f-b1db-9f2d13893774.png">

<br>
	
# User_authentication_service
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">



</div>



<br>

## 🎬 어떤 프로젝트 인가요?

 ‘User_athentication_service’ 웹 어플리케이션은 로그인을 통한 사용자 인증 시스템 프레임을 구성한것 입니다.

스프링 프레임워크를 이용한 프로젝트 진행시 사용자 인증 시스템이 중복되어 공통된 사용자 인증 시스템 형식을 구성하여 이후 다른 프로젝트 진행시 참고할 수 있도록 구성 하였습니다.


<br>

## 💻 시스템 설계

###  ⃝ HashMap을 통해 데이터를 저장 Repository

<img width="318" alt="스크린샷 2022-11-21 오후 5 11 46" src="https://user-images.githubusercontent.com/81874493/202999178-b9e70e48-5d30-41f8-a272-60d01e1b438f.png">

<br>

###  ⃝ Session을 통한 로그인 여부 확인 및 미인증 사용자 접근 제한

<img width="640" alt="스크린샷 2022-11-21 오후 5 15 18" src="https://user-images.githubusercontent.com/81874493/202999935-3a180dd1-bc14-43c7-81d0-e8ea457b70e7.png">

* session을 통한 로그인 여부 확인
  - V1 자체적 SessionManager 방식
  - V2 스프링 프레임워크에서 제공하는 Session 방식 

<br>

* 접근 제한 필터링
  - filter를 통한 미인증 사용자 접근 제한 
    - V1 자체적 SessionManager 방식
    - V2 스프링 프레임워크에서 제공하는 Session 방식  
    
  - interceptor를 통한 미인증 사용자 접근 제한 
    - V1 자체적 SessionManager 방식
    - V2 스프링 프레임워크에서 제공하는 Session 방식  


<br>

###  ⃝ Member 클래스 다이어그램

<img width="167" alt="스크린샷 2022-11-21 오후 5 13 22" src="https://user-images.githubusercontent.com/81874493/203006591-d72a1720-2aee-4140-a8c8-687632b2dedc.png">



<br>

###  ⃝ 회원가입, 로그인 Service 클래스 다이어그램

<img width="404" alt="스크린샷 2022-11-21 오후 5 12 20" src="https://user-images.githubusercontent.com/81874493/203006078-0a38750d-6544-4070-918e-99feb2478643.png">

<br>

###  ⃝ 컨트롤러 클래스 다이어그램

<img width="1181" alt="스크린샷 2022-11-21 오후 5 17 15" src="https://user-images.githubusercontent.com/81874493/203005947-b4f567bd-4b7e-414d-a5b4-9fcd2ffc3a73.png">

<br>


## 📺 프로젝트 실행 화면
  
  [회원 가입]
  
  <img width="457" alt="스크린샷 2022-11-21 오후 5 52 52" src="https://user-images.githubusercontent.com/81874493/203006927-783fa1d5-0d2a-45b4-b327-90f71d9f00d1.png">

<br>

  [로그인]
  
<img width="467" alt="스크린샷 2022-11-21 오후 5 52 59" src="https://user-images.githubusercontent.com/81874493/203006948-b60fc842-f79e-4cb6-8582-8bbfdf9350d1.png">


<br>

  [회원 정보 수정]

<img width="460" alt="스크린샷 2022-11-21 오후 5 53 10" src="https://user-images.githubusercontent.com/81874493/203007021-8acec50c-ce5b-4e4b-b627-3eee40324bdc.png">







