## UbSE Site Server
> 연구실 소개사이트 api를 serving합니다


### Skills
* Java
* Spring Boot
* Mysql
* JPA

### Naming
* Professor : 교수
* ProfessorFiled : 교수 연구 분야
* Undergraduate : 학부생
* UndergraduateFiled : 학부생 분야
* Graduate : 졸업생
* Image : 이미지 저장 경로

### Features
* 학부생 전체 조회
* 학부생 수정
* 학부생 등록
* 학부생 삭제
* 졸업생 전체 조회
* 졸업생 등록
* 졸업생 수정
* 졸업생 삭제
* 교수 수정
* 교수 조회
* 이미지 파일 업로드 기능
* 업로드된 이미지 조회

#### Swagger
* localhost:8000/docs 접속 후 사용

### API 구조

![image](https://github.com/amazon7737/ubse-lab-site-server/assets/76634341/cb66f156-bf62-4739-8d32-d61561df851a)

### 구현 및 추가 설명

* Professor 정보 수정 기능 작성 중 StaleObjectStateException 예외 발생
  * 원인 : 최초 조회 후 영속성이 된 데이터를 save 를 수행한 후 다시 재조회시 트랜잭션 Locking이 발생 (낙관적 락 방식을 제외하고 직접적으로 락을 거는 비관적 락이 더 효율적인 방법임)
  * 기본적으로 Data JPA 메소드가 수행 후 자동 플러시, 커밋 처리를 해주는데 제대로 수행되고 있지 않아서 직접 수동 제어를 하는 방법을 고민하다가 옳은 방법은 아니라고 생각이 들었음
  * save 수행 후 발생하는 select 쿼리를 이용하여 데이터를 확인하는 방법밖에 없어서 List를 작성하여 저장되도록하고 return을 진행하여 테스트코트를 확인함


* 변경감지(Dirty Checking)에 대해서 올바른 이해를 하지 않은 상태로 도메인마다 update 메소드를 작성
  * 문제 : Professor 데이터를 수정하였는데 Graduate 에 등록이 되는 등 네이밍이 같은 변수가 멋대로 흘러다니기 시작함
  * 대처 : update 메소드 네이밍을 구분지어 수정 후 꼬이지는 않음
  * 후기 : 영속성과 비영속성과 관련이 있으나 후에 테스트를 더 해봐야함


* 403 에러 발생 후 AxiosError 발생
  * 해결 : Cors Config 작성 후 해결
