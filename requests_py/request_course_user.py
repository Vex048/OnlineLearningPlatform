import requests
from datetime import datetime

url_get = "http://localhost:8080/api/v1/user/2"
headers = {
    "Content-type": "application/json"
}
user = requests.get(url=url_get,headers=headers)
url_get_course =  "http://localhost:8080/api/v1/course/1"
course = requests.get(url=url_get_course,headers=headers)
url_post = "http://localhost:8080/api/v1/enrollment/addUserToCourse"
body = {
    "enrollment_date": datetime.now().isoformat(),
    "user": user.json(),
    "course": course.json()
}
response = requests.post(url=url_post,headers=headers,json=body)