import requests

API_ENDPOINT = "http://text-processing.com/api/sentiment/"

data = {'text':'good'} 
print data

r = requests.post(url = API_ENDPOINT, data = data) 

pastebin_url = r.text 
print("The pastebin URL is:%s"%pastebin_url) 