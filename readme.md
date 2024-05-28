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
  * 기본적으로 Data JPA 메소드가 수행 후 자동 플러시, 커밋 처리를 해주는데 직접 수동 제어를 하는 방법은 옳지 않다고 생각했음
  * save 수행 후 발생하는 select 쿼리를 이용하여 데이터를 확인하는 방법밖에 없어서 List를 작성하여 저장되도록하고 return을 진행하여 테스트코트를 확인함
