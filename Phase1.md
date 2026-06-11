# Phase 1 Deliverables

## Team Name
Team Tetra

## User Stories
Account Creation: As a new user, I can register an account to start tracking my todo tasks.
  - Navigate to sign up page from log in screen
    - Stretch Goal: Make homepage
  - Create Username & Password
    - Stretch Goal: Password requirements
  - Click on sign up button
  - Check if username already exists
    - Warn if duplicate is found
  - If username doesn’t exist create user
  - Automatically return to login screen


Authentication: As a new user, I can log in and out to securely access my todo items.
  - Enter Username & Password
  - Click login button
  - Check if credentials match DB records
    - Warn if invalid
  - If match is found, return the authentication token
  - Take user to todo screen
  - Frontend request todo list from backend
  - Click logout button
    - Deletes token and returns to login page

Task Management: As a user, I can create, edit, and delete todo items to keep track of my work.
  - Create
    - Fill out Todo name field
    - Click add todo
    - Backend will insert Todo into Todo table
  - Edit (Change name/mark complete)
    -  Click edit button
    -  Edit name field
    -  Click save edit
    -  Send an update to backend
    -  —--------------------
    - Click Todo checkmark box
    -  Send an update to backend
  -  Delete
     -  Click trashcan
     - Prompt warning message
     - Send delete to backend, which also deletes subtasks

Subtask Organization: As a user, I can create, edit, and delete subtask items to better organize my primary tasks.
- Create
  - Fill out subtask name field
  - Click add todo
  - Backend will insert subtask into subtask table
- Edit (Change name/mark complete)
  - Click edit button
  - Edit name field
  - Click save edit
  - Send an update to backend
  -  —--------------------
  - Click subtask checkmark box
  - Send an update to backend
- Delete 
  - Click trashcan
  - Prompt warning message
  - Send delete to backend

## Database Model
### Todos
  - todo_ID(PK), Name(alt_PK), user_Id(FK), Completed(boolean)
  - If Todo is deleted, it also needs to remove its child subtasks

### Subtasks
  - todo_ID(Parent)(FK), Name(PK), Completed(boolean)
    
### Users
  - user_Id(PK), Username, password, (email)
<img width="336" height="586" alt="Image20260611100619" src="https://github.com/user-attachments/assets/e9128677-cc6d-47ec-9868-91a7e658f45e" />

## API Contract
- Username duplicate check
  - Mack’s guess: GET Users/"username"
- Login Request - `{Username, password}`
- Logout - `{user_id}`
- Register account request - `{Username, password}`
- Add Todo - `{todo_id, Title, user_id, completed(false)}`
- Get Todos - DB return Todo object list
- Update Todo - `{todo_id, Title, user_id, completed}`
- Delete Todo - `{todo_id}`
  - Auto delete subtasks
- Add Subtask - `{subtask_id, Title, user_id, completed(false)}`
- Get Subtasks - DB return subtask object list
- Update Subtask - `{subtask_id, Title, user_id, completed}`
- Delete Subtask - `{subtask_id}` 

## Definition of Done

- [ ] Successfully create a new user  
  - Blocks duplicate  
  - New account reflected in database

- [ ] Successfully login/logout to created account  
  - Checks credential validity  

- [ ] Successfully retrieves all todos with proper status’ and names  
  - Doesn’t show deleted todos  

- [ ] Can edit Todo and subtask name and changes persist  
- [ ] Can update Todo/Subtask completion status and change persists  
- [ ] Can successfully delete Todo/subtask and deletion persists  
- [ ] Database changes are verified


