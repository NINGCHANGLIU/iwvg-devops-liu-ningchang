## Máster en Ingeniería Web – UPM
## Ingeniería Web: Visión General (IWVG) – DevOps

Proyecto académico de la asignatura **IWVG DevOps** del Máster en Ingeniería Web (UPM).  
El proyecto sigue estrictamente el flujo **GitFlow** definido por el profesorado y se desarrolla de forma incremental mediante **Sprints**, **Issues**, **Pull Requests**, **CI/CD** y **Releases versionadas**.

---

## 📊 Estado actual del proyecto

**Sprint 1, Sprint 2 y Sprint 3 completados**  
Todo el desarrollo requerido hasta **Sprint 3** ha sido implementado, testeado y fusionado correctamente en `develop`, con **Quality Gate de SonarCloud superado** y **CI/CD funcional**.

---

## 🔄 Integración continua (CI)

[![CI](https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang/actions/workflows/continuous-integration.yml/badge.svg)](
https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang/actions/workflows/continuous-integration.yml
)

- Ejecución automática de `mvn clean verify`
- Ejecución de tests unitarios y funcionales
- Generación de cobertura con **JaCoCo**
- Análisis de calidad en **SonarCloud**

---

## 🧪 Calidad del código

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=NINGCHANGLIU_iwvg-devops-liu-ningchang&metric=alert_status)](
https://sonarcloud.io/summary/new_code?id=NINGCHANGLIU_iwvg-devops-liu-ningchang
)

- Quality Gate **PASSED**
- Cobertura en *New Code* ≥ umbral exigido
- Sin *Security Hotspots* pendientes
- Sin duplicaciones en código nuevo

---

## 🚀 Despliegue continuo (CD)

[![Render](https://img.shields.io/badge/Render-Deployed-success)](
https://iwvg-devops-docker.onrender.com
)

- Construcción automática de imagen Docker en `master`
- Publicación en **GitHub Container Registry (GHCR)**
- Despliegue automático en **Render** mediante Deploy Hook

---

## 🚀 Funcionalidades implementadas

### Sprint 1 – Ecosistema DevOps
- Configuración de **GitHub Actions (CI)**
- Integración con **SonarCloud**
- Pipeline de **CD con Docker + Render**
- Estructura GitFlow: `issue → develop → release → master`

### Sprint 2 – Modelo de dominio
- Clase `Fraction` y `FractionTest`
- Clase `User` y `UserTest`
- Clase `UsersDatabase` y `UsersDatabaseTest`
- Validaciones, excepciones y cobertura completa

### Sprint 3 – Búsquedas (Search)
Implementación completa de los métodos solicitados en `UsersDatabase`:

- **Search 0** – `findUserFamilyNameInitialBySomeProperFraction`
- **Search 4** – `findFirstDecimalFractionByUserName`
- **Search 8** – `findUserFamilyNameBySomeImproperFraction`
- **Search 9** – `findHighestFraction`

Cada búsqueda incluye:
- Implementación conforme a la definición del profesor
- Tests unitarios mínimos y suficientes
- Cobertura de *New Code* verificada en SonarCloud
- PR individual con CI y Quality Gate en verde

---

## 🛠️ Tecnologías utilizadas
- **Java**
- **Maven**
- **JUnit 5**
- **GitHub Actions**
- **SonarCloud**
- **Docker**
- **Render**
- **Spring Boot**

---

## 📦 Releases

- **v5.0.0-release** — Sprint 1 (DevOps setup completo)
- **v5.1.0-release** — Sprint 2 (Modelo de dominio)
- **v5.2.0-release** — Sprint 3 (Search 0, 4, 8 y 9)

Cada release sigue el flujo:
`develop → release-x.y.z → master → tag → merge back to develop`

---

## 📎 Repositorio
https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang
