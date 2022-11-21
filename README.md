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

스프링 프레임워크를 이용한 프로젝트 진행시 사용자 인증 시스템이 중복되어 사용자 인증 시스템 프레임을 구성하여 향 후 다른 프로젝트 진행시 참고할 수 있도록 구성하였습니다.


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

* 접근 제한 필터링
  - filter를 통한 미인증 사용자 접근 제한 
    - V1 자체적 SessionManager 방식
    - V2 스프링 프레임워크에서 제공하는 Session 방식  
    
  - interceptor를 통한 미인증 사용자 접근 제한 
    - V1 자체적 SessionManager 방식
    - V2 스프링 프레임워크에서 제공하는 Session 방식  


* view template
  - thymeleaf 사용
  - bootstrap 사용

<br>

## 📺 프로젝트 실행 화면
  
  [회원 가입]
  
  <img width="602" alt="스크린샷 2022-01-03 오전 12 02 32" src="https://user-images.githubusercontent.com/81874493/147880662-d38cdecb-2e96-40dd-bdcb-ecfdaab7aa4c.png">
  
<br>

  [로그인]
  
  <img width="620" alt="스크린샷 2022-01-03 오전 12 02 40" src="https://user-images.githubusercontent.com/81874493/147880691-2f2cdbb9-bc11-496b-80df-bdb3fc6516e2.png">





# login Service
## Spring Framework를 이용한 Web Application 공부

<br>

login Service 구성 









