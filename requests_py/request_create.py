import requests

users = [
    {
        "email": "john.doe@example.com",
        "password": "password123",
        "username": "johndoe",
        "firstName": "John",
        "lastName": "Doe"
    },
    {
        "email": "jane.smith@example.com",
        "password": "securepass456",
        "username": "janesmith",
        "firstName": "Jane",
        "lastName": "Smith"
    },
    {
        "email": "michael.johnson@example.com",
        "password": "mjohnson789",
        "username": "michaelj",
        "firstName": "Michael",
        "lastName": "Johnson"
    },
    {
        "email": "emily.brown@example.com",
        "password": "brownemi567",
        "username": "emilybr",
        "firstName": "Emily",
        "lastName": "Brown"
    },
    {
        "email": "david.wilson@example.com",
        "password": "dwilson234",
        "username": "davidw",
        "firstName": "David",
        "lastName": "Wilson"
    },
    {
        "email": "sarah.taylor@example.com",
        "password": "staylor890",
        "username": "saraht",
        "firstName": "Sarah",
        "lastName": "Taylor"
    },
    {
        "email": "robert.martinez@example.com",
        "password": "rmart321",
        "username": "robertm",
        "firstName": "Robert",
        "lastName": "Martinez"
    },
    {
        "email": "lisa.anderson@example.com",
        "password": "lisa4567",
        "username": "lisaa",
        "firstName": "Lisa",
        "lastName": "Anderson"
    },
    {
        "email": "kevin.thomas@example.com",
        "password": "kthomas987",
        "username": "kevint",
        "firstName": "Kevin",
        "lastName": "Thomas"
    },
    {
        "email": "jessica.garcia@example.com",
        "password": "jgarcia654",
        "username": "jessicag",
        "firstName": "Jessica",
        "lastName": "Garcia"
    }
]

url = "http://localhost:8080/api/v1/user/registerMultipleUsers"
headers = {
    "Content-type": "application/json"
}
response = requests.post(url=url,headers=headers,json=users)
