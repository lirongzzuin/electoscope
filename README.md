# 🗳️ ElectoScope

**ElectoScope**는 제21대 대한민국 대통령 선거를 맞아,  
선거와 관련된 방대한 뉴스를 자동으로 수집·요약하고,  
후보별 공약을 주제별로 시각적으로 비교할 수 있도록 구성한 **AI 기반 정치 정보 플랫폼**입니다.

AI 요약, 감성 분석, 실시간 뉴스 크롤링, 공약 관리, 통계 시각화 등  
**실전 서비스 수준의 백엔드 아키텍처와 프론트엔드 인터페이스**를 통합하여 구현했습니다.

---

## ✨ 주요 기능 요약

- **네이버 뉴스 실시간 크롤링**  
  `https://news.naver.com/election/president2025/news`에서 대선 관련 기사를 자동 수집하며,  
  각 뉴스의 본문을 추출해 요약과 감성 분석에 활용합니다.

- **AI 요약 및 감성 분석 (HuggingFace API)**
  - 뉴스 요약: `facebook/bart-large-cnn`
  - 감성 분석: `cardiffnlp/twitter-roberta-base-sentiment`  
    HuggingFace Inference API를 활용해 뉴스 본문을 요약하고, 감정 분류 결과(긍정/중립/부정)를 시각화합니다.

- **Redis 기반 캐싱 처리**  
  동일 뉴스 또는 공약 본문에 대해 반복 분석 요청 시, Redis를 활용해 중복 처리를 방지하고 속도와 비용을 최적화합니다.

- **후보별 공약 비교 UI**  
  주요 이슈(청년, 경제, 복지, 외교 등)에 대해 각 후보의 공약을 시각적으로 비교할 수 있는 테이블 형태 UI를 제공합니다.  
  공약은 감성 분석을 통해 어조(긍정/부정 등)가 함께 시각화됩니다.

- **공약 수동 등록 및 자동 감성 분석**  
  관리자는 웹 UI를 통해 공약을 직접 등록할 수 있으며, 등록 즉시 AI 기반 감성 분석이 자동 적용됩니다.

- **사용자 뉴스 요약 요청 기능**  
  사용자가 뉴스 본문을 직접 입력하여 AI 요약 및 감성 분석 결과를 실시간으로 확인할 수 있습니다.

- **후보 언급량 통계 시각화**  
  뉴스에서 각 후보가 언급된 빈도를 통계적으로 분석하여 막대 차트로 시각화합니다.

---

## ⚙️ 기술 스택

### Backend
- Java 17 / Spring Boot 3.x
- Spring Web / Data JPA / Redis
- Spring Scheduler / Retry / Kafka 구조 참고
- HuggingFace Inference API 연동
- Jsoup (HTML 파싱)
- H2 (개발 및 배포용 인메모리 DB)

### Frontend
- React + TypeScript (Vite)
- TailwindCSS / shadcn/ui
- React Router / Axios

---

## 📁 프로젝트 구조

```
electoscope/
├── backend/           # Spring Boot 백엔드 서버
│   ├── news/          # 뉴스 요약 Entity, DTO, Service
│   ├── crawler/       # 뉴스 크롤러 (목록, 본문 파서)
│   ├── controller/    # API 컨트롤러
│   ├── service/       # 공약, 뉴스, 통계 비즈니스 로직
│   └── util/          # AI 클라이언트, Redis 유틸
└── frontend/          # React + Vite 프론트엔드
    ├── pages/         # 라우팅별 화면 구성
    ├── components/    # 재사용 컴포넌트
    └── data/          # 공약 데이터 예시
```

---

## 🧪 로컬 실행 방법

### 1. 백엔드 실행

```bash
cd backend
./gradlew bootRun
```

- 기본 포트: `http://localhost:8080`
- HuggingFace API 키는 `.env` 또는 `application-prod.properties`에 설정

### 2. 프론트엔드 실행

```bash
cd frontend
npm install
npm run dev
```

- 실행 주소: `http://localhost:5173`

---

## ⛳ 구현 기능 체크리스트

- [x] 프로젝트 구조 설계 및 멀티 레이어 아키텍처 구성
- [x] 뉴스 자동 크롤링 및 본문 파싱 (Jsoup 기반)
- [x] AI 요약 / 감성 분석 연동 (HuggingFace API)
- [x] Redis 캐시 연동을 통한 중복 처리 최적화
- [x] 뉴스 저장 + 감성 분석 결과 DB 반영
- [x] 후보별 공약 비교 UI + 감성 결과 표시
- [x] 수동 공약 등록 화면 구현 및 POST API 연동
- [x] 홈 화면 구성 및 CTA 링크 정리
- [x] 후보 언급량 통계 분석 및 시각화 그래프
- [ ] 공약 수정/삭제 기능 (예정)
- [ ] 관리자 인증 (예정)

---

## 👤 작성자 소개

**이영균 (YoungGyun Lee)**
> 실시간 데이터 처리와 고신뢰 백엔드 시스템 구성에 관심이 많습니다.  
> 단순한 기능 구현을 넘어서 **전체 서비스 흐름과 구조를 이해하고 설계**하는 역량을 키워가고 있습니다.  
> 특히 공공 데이터, AI 요약, 시각화를 연계한 실용적인 프로젝트에 도전하며,  
> Java/Spring 기반의 백엔드 기술과 Redis, Kafka, REST API 설계 경험을 쌓기위해 노력 중다입니.

GitHub: [@lirongzzuin](https://github.com/lirongzzuin)

---

## 📄 라이선스

MIT License