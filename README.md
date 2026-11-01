# AI-Powered IT Service Desk

An AI-powered IT Service Desk application designed to manage IT support tickets, track ticket lifecycle, handle service requests, and eventually provide intelligent troubleshooting assistance.

The project is being developed as a full-stack application using Spring Boot, React, and MySQL.

> Current phase: Core Spring Boot Ticket Management REST API completed.  
> AI features and React frontend are planned for future phases.

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Validation

### Database
- MySQL

### Frontend
- React
- CSS

Frontend development is planned for a later phase.

### Tools
- IntelliJ IDEA
- Postman
- Git
- GitHub

## Backend Architecture

The backend follows a layered architecture:

Client / Frontend  
→ Controller  
→ Service  
→ Repository  
→ MySQL Database

Additional supporting layers include:

- DTOs for API request and response models
- Mapper for Entity ↔ DTO conversion
- Global exception handling
- Enums for ticket priority and status

## Implemented Features

### Create Ticket

Creates a new IT support ticket.

New tickets are automatically created with the status:

`OPEN`

### Get All Tickets

Retrieves all tickets stored in the system.

### Get Ticket by ID

Retrieves a specific ticket using its ID.

If the ticket does not exist:

`404 Not Found`

### Update Ticket Details

The following ticket information can be updated:

- Title
- Description
- Category
- Priority

The following fields are preserved:

- ID
- Created timestamp
- Status

### Update Ticket Status

Ticket status is updated separately using a PATCH endpoint.

Current ticket statuses include:

- `OPEN`
- `IN_PROGRESS`
- `RESOLVED`

### Delete Ticket

A ticket can be deleted using its ID.

Successful deletion returns:

`204 No Content`

If the ticket does not exist:

`404 Not Found`

## REST API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/tickets` | Create a new ticket |
| GET | `/api/tickets` | Get all tickets |
| GET | `/api/tickets/{id}` | Get ticket by ID |
| PUT | `/api/tickets/{id}` | Update ticket details |
| PATCH | `/api/tickets/{id}/status` | Update ticket status |
| DELETE | `/api/tickets/{id}` | Delete ticket |

## Validation

Request validation is implemented using Jakarta Validation.

Validated fields include:

- Title
- Description
- Category
- Priority
- Status

Invalid requests return:

`400 Bad Request`

## Exception Handling

The application uses centralized exception handling.

### Validation Errors

Handled using:

`MethodArgumentNotValidException`

Returns:

`400 Bad Request`

### Ticket Not Found

Handled using the custom:

`TicketNotFoundException`

Returns:

`404 Not Found`

### Invalid Request Data

Invalid enum values or unreadable JSON data are handled using:

`HttpMessageNotReadableException`

Returns:

`400 Bad Request`

## Ticket Lifecycle

The current ticket workflow is:

`OPEN → IN_PROGRESS → RESOLVED`

Additional rules for controlling valid status transitions will be added later.

## Project Structure

The backend is organized into packages for:

- Controller
- Service
- Repository
- Entity
- DTO
- Mapper
- Enums
- Exception Handling

This separation keeps the application modular and maintainable.

## Database Configuration

The application uses MySQL.

Environment variables are used for database credentials.

Required environment variables:

`DB_USERNAME`

`DB_PASSWORD`

The database URL currently points to:

`service_desk`

Database passwords and other secrets are not stored in source control.

## Current Progress

Completed:

- Spring Boot project setup
- MySQL integration
- Ticket entity
- Repository layer
- DTO layer
- Mapper layer
- Service layer
- Controller layer
- Ticket CRUD operations
- Ticket status update
- Request validation
- Global exception handling
- Custom ticket-not-found exception
- Invalid enum handling
- Manual API testing using Postman

## Planned Features

Future development will include:

- Backend cleanup and refactoring
- Consistent API error response structure
- Ticket filtering
- Ticket searching
- Priority and status filtering
- Ticket status transition rules
- Ticket assignment to support agents
- Ticket comments and history
- AI-powered ticket analysis
- AI-generated troubleshooting suggestions
- Automatic category suggestions
- Automatic priority suggestions
- React frontend
- Frontend and backend integration
- Authentication and authorization
- User, Support Agent, and Admin roles
- Automated testing
- Swagger / OpenAPI documentation
- Deployment

## AI Vision

The long-term goal is to transform the ticket management system into an intelligent IT Service Desk.

For example, a user may report:

> Laptop is unable to connect to Wi-Fi.

The future AI layer may help with:

- Issue analysis
- Category suggestion
- Priority suggestion
- Troubleshooting recommendations
- Ticket summarization
- Possible resolution suggestions
- Support-agent assistance

AI functionality has not yet been implemented.

## Running the Backend Locally

Requirements:

- Java
- MySQL
- Maven or Maven Wrapper

Before starting the application:

1. Create the `service_desk` MySQL database.
2. Configure the `DB_USERNAME` environment variable if required.
3. Configure the `DB_PASSWORD` environment variable.
4. Start the Spring Boot application.
5. Test the REST APIs using Postman.

The backend currently runs on:

`http://localhost:8080`

## Development Approach

This project is being developed incrementally.

Each feature is:

1. Designed
2. Implemented
3. Manually tested
4. Committed to Git
5. Pushed to GitHub

The repository will continue to evolve as additional backend, frontend, AI, testing, and deployment features are implemented.

## Author

Akash