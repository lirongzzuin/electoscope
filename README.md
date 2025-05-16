# 🗳️ ElectoScope

ElectoScope는 21대 대한민국 대통령 선거를 맞아,  
복잡하고 방대한 선거 뉴스를 요약하고, 후보별 공약을 주제별로 정리하여 전달하는 AI 기반 정보 서비스입니다.  
실시간 뉴스 흐름 감지, 공약 비교, 요약 자동화, 언급량 분석 등 다양한 기능을 중심으로 구성하였고,  
기술적으로는 Kafka, Redis, Spring Boot, AI 모델 연동 등 실전 백엔드 기술을 중점적으로 구현하고 있습니다.

---

## ✨ 주요 기능 요약

- **실시간 뉴스 수집 및 AI 요약**  
  HuggingFace의 뉴스 요약 모델을 활용해 주요 대선 키워드에 대한 기사를 자동으로 요약합니다.

- **후보별 공약 비교 뷰**  
  경제, 복지, 청년, 외교 등 카테고리별로 후보 공약을 직관적으로 비교할 수 있습니다.

- **후보 언급 트렌드 시각화 (예정)**  
  뉴스에서 후보가 언급된 빈도를 분석하여 시각화하는 기능을 구현할 예정입니다.

- **감성 분석 (예정)**  
  요약된 뉴스의 정서(긍정/부정/중립)를 분석하여 사용자에게 직관적으로 전달할 수 있도록 준비 중입니다.

- **회원가입 없이 전체 기능 사용 가능**  
  사용자 편의성을 고려하여 별도 로그인 없이도 뉴스 열람, 공약 비교, 공유 기능 등을 사용할 수 있도록 설계하였습니다.

---

## ⚙️ 기술 스택

### Backend
- Spring Boot 3.1+
- Spring Web / Data JPA / Redis / Kafka
- Spring Retry / Scheduling
- HuggingFace Inference API 연동
- H2 (개발용 DB)

### Frontend
- Vite + React + TypeScript
- TailwindCSS / shadcn/ui
- React Router

---

## 📁 프로젝트 구조

```
electoscope/
├── backend/       # Spring Boot 기반 백엔드 서버
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
- Swagger 연동 예정

### 2. 프론트엔드 실행

```bash
cd frontend
npm install
npm run dev
```

- 실행 주소: `http://localhost:5173`

---

## ⛳ 진행 상황

- [x] 프로젝트 전체 구조 세팅
- [x] Tailwind 적용 및 홈 화면 구성
- [x] HuggingFace 뉴스 요약 연동 준비
- [ ] 뉴스 요약 API 구현
- [ ] 공약 비교 기능 화면 구성
- [ ] 백엔드 ↔ 프론트 연동
- [ ] 실제 서비스 배포

---

## 👤 작성자

**이영균 (YoungGyun Lee)**
> 실시간 데이터 처리와 확장 가능한 백엔드 아키텍처에 관심이 많습니다.  
> 기술을 통해 사회적으로 의미 있는 문제를 더 쉽고 명확하게 전달하는 서비스에 가치를 두고 있습니다.  
> AI, 메시징 시스템, API 설계, 데이터 기반 분석 등을 활용한 실용적인 백엔드 기술 구현에 집중하고 있습니다.

GitHub: [@lirongzzuin](https://github.com/lirongzzuin)

---

## 📄 라이선스

MIT License