---
marp: true
theme: default
paginate: true
footer: What2Eat - presentation example
# No number and footer on the first slide
_paginate: false
_footer: " "
---

<!-- This reserves 30% of the slide width for the image on the right. The image itself is scaled to 80% size. -->
![bg right:30% 80%](https://www.htwg-konstanz.de/fileadmin/pub/allgemein/Grafiken/logo/logo_pos.svg)

# What2Eat

## Presentation example (with Marp)

by Johannes Schneider

---

# A note on Marp

<!-- Das reserviert 40% der Folien-Breite für das Bild auf der linken Seite. Das Bild an sich wird auf 90% Größe skaliert -->
![bg left:40% 90%](https://avatars.githubusercontent.com/u/20685754?s=200&v=4)

Marp (also known as the Markdown Presentation Ecosystem) provides an intuitive experience for creating beautiful slide decks. You only have to focus on writing your story in a Markdown document.

---

# Introduction

What2Eat is a simple app for showing the meal of the day at the cafeteria of the university of Kaiserslautern. It serves as an example for illustrating architecture design (part of the lecture "SWAR-WIN: Softwarearchitektur" by Prof. Dr. Johannes C. Schneider from [HTWG Konstanz](https://www.htwg-konstanz.de/)).

---

# Stakeholders

Stakeholders of this project and system are

- the teacher of the lecture _Software-Architektur_ (project lead),
- attendants of the lecture _Software-Architektur_, WIN and GIB students ("users" in terms of learning about software architecture) and
- potential cafeteria customers (end users).

---

# System context delineation

![w:1000px](diagrams/system-context-RT.drawio.svg)

---

<!-- custom style to adapt the font size -->
<style scoped>
section {
  font-size: 19pt
}
</style>

# Driver Package Size

| Categorization  |                                                                                                                |                    |
| --------------- | -------------------------------------------------------------------------------------------------------------- | ------------------ |
| Scenario Name     | Package Size                                                                                                   |        |                 |
| Scenario ID       | Q.Size                                                                                                         |
| **Description** |                                                                                                                | **Quantification** |
| Environment     | The code is ready for a new deployment.                                                                        |                    |
| Stimulus        | A new deployment artifact is built to update the production app.                                               |                    |
| Response        | In order to make the deployment from places with weak internet connection, the deployment artifact has size S. | S &le; 3 MB        |

---

<!--- This pictures is taken as full background -->

![bg](https://images.unsplash.com/photo-1584890280660-9322ee35baf1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1668&q=80)
