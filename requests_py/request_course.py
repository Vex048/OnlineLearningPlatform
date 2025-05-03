import requests


url_get = "http://localhost:8080/api/v1/user/1"
headers = {
    "Content-type": "application/json"
}
user = requests.get(url=url_get,headers=headers)
body = {
    "description": "Jakis opis",
    "title": "Jakis tytul",
    "owner":user.json()
}
url_post = "http://localhost:8080/api/v1/course/addCourse"
response = requests.post(url=url_post,headers=headers,json=body)
print(response)