# 🗳️ ElectoScope

ElectoScope는 21대 대한민국 대통령 선거를 맞아,  
방대한 선거 뉴스를 자동으로 수집하고 요약하며, 후보별 공약을 주제별로 비교하여 전달하는 **AI 기반 정보 서비스**입니다.  
실시간 뉴스 크롤링, AI 요약 및 감성 분석, 공약 비교, Redis 캐싱, REST API 제공 등  
실전 서비스 수준의 백엔드 아키텍처와 AI 통합 기술을 중점적으로 구현하고 있습니다.

---

## ✨ 주요 기능 요약

- **네이버 뉴스 실시간 크롤링**  
  `https://news.naver.com/election/president2025/news`에서 대선 관련 기사를 자동 수집하고,  
  기사 본문을 추출하여 요약 및 감성 분석에 활용합니다.

- **HuggingFace 기반 AI 뉴스 요약 및 감성 분석**  
  크롤링한 본문을 AI 모델(`facebook/bart-large-cnn`)로 요약하고,  
  요약 결과를 감성 모델(`nlptown/bert-base-multilingual-uncased-sentiment`)로 분석합니다.

- **Redis 캐싱으로 성능 최적화**  
  동일 본문에 대한 중복 요약 요청 시 Redis 캐싱을 통해 응답 속도 개선 및 API 비용 절감

- **후보별 공약 비교 테이블**  
  청년, 경제, 복지, 외교 등 주제별로 후보의 공약을 시각적으로 비교할 수 있는 UI 제공

- **프론트 요약 요청 기능 & 감성 결과 시각화**  
  사용자가 뉴스 본문을 직접 입력하면 요약 및 감정 결과를 즉시 확인 가능 (이모지 포함)

- **향후 확장 예정**
  - 후보 언급량 통계 분석 및 시각화
  - 요약 뉴스 피드화 (DB 저장 → 목록 API 제공 → 프론트 피드 구성)

---

## ⚙️ 기술 스택

### Backend
- Spring Boot 3.1+
- Spring Web / Data JPA / Redis / Kafka
- Spring Retry / Scheduling
- HuggingFace Inference API 연동
- Jsoup (HTML 파싱)
- H2 (개발용 인메모리 DB)

### Frontend
- Vite + React + TypeScript
- TailwindCSS / shadcn/ui
- React Router / Axios

---

## 📁 프로젝트 구조

```
electoscope/
├── backend/       # Spring Boot 기반 백엔드 서버
│   ├── news/      # 뉴스 요약 저장용 엔티티, DTO, 서비스
│   ├── crawler/   # 네이버 뉴스 크롤러 관련 클래스
│   └── util/      # HuggingFace API, Redis 유틸리티
└── frontend/      # Vite + React 기반 사용자 인터페이스
```

---

## 🧪 로컬 실행 방법

### 1. 백엔드 실행

```bash
cd backend
./gradlew bootRun
```

- 기본 포트: `http://localhost:8080`
- HuggingFace API 키는 `.env` 또는 `application-prod.properties`에 설정 필요

### 2. 프론트엔드 실행

```bash
cd frontend
npm install
npm run dev
```

- 실행 주소: `http://localhost:5173`

---

## ⛳ 현재 구현 완료된 기능

- [x] 전체 프로젝트 초기 구조 설계 및 기술 스택 구성
- [x] 뉴스 본문 요약 API 구현 (HuggingFace 연동)
- [x] 감성 분석 연동 및 결과 시각화
- [x] Redis 캐싱으로 성능 최적화
- [x] 네이버 대선 뉴스 실시간 크롤링 구현
- [x] 뉴스 본문 추출 및 자동 요약 + 감정 분석 통합 흐름
- [x] 요약된 뉴스 저장을 위한 Entity/Repository 구성
- [x] 프론트에서 요약 요청 및 감성 결과 출력 구현
- [x] 후보별 공약 비교 화면 구현
- [ ] 저장된 요약 뉴스 피드 API 생성 및 프론트 연동
- [ ] 후보 언급량 분석 및 시각화 (예정)

---

## 👤 작성자

**이영균 (YoungGyun Lee)**
> 실시간 데이터 처리와 확장 가능한 백엔드 아키텍처에 관심이 많습니다.  
> AI 기반 서비스와 공공 데이터 활용을 연결하는 데 가치를 두고,  
> 기술을 통해 사회적으로 의미 있는 정보를 누구나 쉽게 접할 수 있도록 만드는 데 집중합니다.  
> Java/Spring 기반 백엔드 개발에 강점을 가지고 있으며, Redis, Kafka, API 설계, AI 연동 경험이 있습니다.

GitHub: [@lirongzzuin](https://github.com/lirongzzuin)

---

## 📄 라이선스

MIT License