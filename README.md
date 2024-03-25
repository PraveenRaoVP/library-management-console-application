# Zoho School for Graduate Studies: Library Management System

This Library Management System is a B2C application developed for admin users. It was developed using Java and completed within a development time of 3 days.
## Architecture and Flow of the Application
![Imgur](https://i.imgur.com/dCxvIPZ.png)

## Screenshots
### Login Screen:-
![Imgur](https://i.imgur.com/MeSmfLm.png)

### Adding New Books to The Library:-
![Imgur](https://i.imgur.com/XXvpFtq.png)

### Issuing Books:-
![Imgur](https://i.imgur.com/76lPK2I.png)

### Return Books:-
![Imgur](https://i.imgur.com/VNjkiba.png)

## Features

### Admin

- Admin user Login / Logout (session maintenance)
- Library setup with the basic details
- Add new books to the library
- New account creation for customers
- Search and showing a books list to the user
- Assign books to the user with the return date
- Return process and overdue handling
- Managing the books
- Managing the user account

## Model Classes

### Library

- name: String
- id: String
- phoneNo: long
- emailId: String
- address: String
- adminUsers: List<userId>

### Credentials

- userId: String
- password: long

### Admin

- name: String
- userId: String
- emailId: String
- address: String

### Book

- name: String
- author: String
- publication: String
- id: long
- edition: String
- availability: boolean
- journey: String
- volume: int

### User

- userId: int
- userName: String
- phoneNo: long
- emailId: String
- address: String
- membership: boolean