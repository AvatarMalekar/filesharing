#below functionalities are implemented in the project

1. An endpoint that will accept a single file and a passcode from a user and upload it. T
file will be encrypted using the passcode and stored. Passcode will not be stored on t
server. Return a unique URL to download the fil
a. Anyone with access to the server should not be able to decrypt the file at th
point without the passcode.

2. The unique URL should take passcode as input, and download a decrypted file for t
user.
3. Files uploaded more than 48 hrs ago should be deleted automatically. URL should return
404 after 48 hours.

#curls for endpoints
1. To upload the file
   
curl --location 'http://localhost:8080/files/upload' \
--form 'file=@"/C:/Users/AVATAR/Downloads/form_to_sent_to_banglore.pdf"' \
--form 'passcode="abscrldhecbrolsf_giadguidag_uagduigd"'


2.To download the file

2. curl --location 'http://localhost:8080/files/download/3d92df94-f5c6-4309-a8ef-8152f80175a5?passcode=abscrldhecbrolsf_giadguidag_uagduiga'
