## Máster en Ingeniería Web – UPM
## Ingeniería Web: Visión General (IWVG) – DevOps

Proyecto académico de la asignatura **IWVG DevOps** del Máster en Ingeniería Web (UPM).  
El objetivo del proyecto es aplicar **DevOps, GitFlow, CI/CD y control de calidad** siguiendo estrictamente el enunciado oficial de la asignatura.

---

## 📊 Estado del proyecto

### Integración continua
[![CI](https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang/actions/workflows/continuous-integration.yml/badge.svg)](
https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang/actions/workflows/continuous-integration.yml
)

### Calidad del código
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=NINGCHANGLIU_iwvg-devops-liu-ningchang&metric=alert_status)](
https://sonarcloud.io/summary/new_code?id=NINGCHANGLIU_iwvg-devops-liu-ningchang
)

### Despliegue continuo
[![Render](https://img.shields.io/badge/Render-Deployed-success)](
https://iwvg-devops-docker.onrender.com
)

---

## 🚀 Funcionalidades por sprint

### Sprint 1 – Preparación del ecosistema
- Integración continua con **GitHub Actions**
- Análisis de calidad y cobertura con **SonarCloud**
- Despliegue continuo en **Render**
- Flujo GitFlow básico (`develop` / `master`)

### Sprint 2 – Preparación del software
- Implementación de:
    - `Fraction` y `FractionTest`
    - `User` y `UserTest`
    - `UsersDatabase`
- Extensión de `Fraction`:
    - `isProper`, `isImproper`, `isEquivalent`
    - `add`, `multiply`, `divide`

### Sprint 3 – Búsquedas
Implementación de búsquedas con tests asociados, incluyendo:
- Búsqueda de la fracción más alta
- Búsquedas por usuario y fracciones propias / impropias
- Operaciones decimales y filtrados avanzados

### Bug
- Corrección de la **Search 4** según el enunciado:
    - Mejora del criterio de selección de fracciones
    - Nueva liberación tras el bug fix

---

## 🛠️ Tecnologías
Java · Maven · GitHub Actions · SonarCloud · Docker · Render · Spring Boot

---

## 📦 Releases
- **v5.0.0-release** — Sprint 1: Ecosistema DevOps
- **v5.1.0-release** — Sprint 2: Modelo y lógica de dominio
- **v5.2.0-release** — Sprint 3: Búsquedas y consultas
- **v5.2.1-release** — Bug fix: mejora de Search 4

---

## 📎 Repositorio
https://github.com/NINGCHANGLIU/iwvg-devops-liu-ningchang
