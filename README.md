# Signal Flow Graph & Routh Criterion Stability Checker
A web-based application for analyzing signal flow graphs and checking the stability of linear systems using the Routh-Hurwitz criterion. This tool is built with Spring Boot for the backend and Vue.js for the frontend, providing a user-friendly interface for engineers and researchers.

## Overview
The application allows users to input signal flow graphs and automatically perform stability analysis using the Routh-Hurwitz criterion. It provides a comprehensive and intuitive interface for designing, analyzing, and validating control systems.

## Features
- **Signal Flow Graph Analysis**:
  - **Graph Input**: Input and visualize signal flow graphs using an interactive graphical interface.
  - **Mason's Gain Formula**: Automatically calculate the transfer function from the signal flow graph.

- **Routh-Hurwitz Stability Criterion**:
  - **Polynomial Input**: Enter characteristic polynomials for stability analysis.
  - **Stability Checking**: Compute the Routh array and determine system stability based on the Routh-Hurwitz criterion.
  - **Stability Report**: Generate and display detailed reports on system stability.

- **Interactive User Interface**:
  - **Drag-and-Drop Graph Editor**: Easily create and modify signal flow graphs.
  - **Real-Time Feedback**: Instant calculations and feedback on stability and transfer functions.

## Technologies Used

- **Frontend**:
  - **Vue.js**: A progressive JavaScript framework for building interactive and dynamic user interfaces.
  - **Bootstrap**: For responsive design and styling.

- **Backend**:
  - **Spring Boot**: A robust framework for developing backend services and APIs.
  - **RESTful API**: Provides endpoints for processing signal flow graphs, performing stability checks, and managing user data.
