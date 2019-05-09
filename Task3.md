# Task3: Solve a bug
The steps involved in changing the existing JWT password in secured way:

**When a user forget their password, we need to reset/change the existing password.**
Can't
- We can't send existing password - it means password is stored in text & can be decrypted and not secured.
- We can't send temporary password in email because if user forget to change then app will be insecured

Can
- JWT(encoded) - user info+ signature, (decoded)-validate with token.
- After validation, JWT allow to create new secured password.

Functionality:
1) Form that accept the email id  - user who has forgotten their password
2) Create a link with a JWT token embedded in the URL. The user will click this link and be allowed to reset their password.
3) Create a page for resetting the password. This page will require the token and will decode it to ensure it is valid.
4) Once the token has been successfully validated, a form will be displayed allowing the user to reset their password.

Steps: 
URL: https://github.com/Patwa23/klm-travel-assessment/blob/master/screenshot/reset-password-workflow-preview-opt.png

JWT:
A JSON Web Token (JWT), in its simplest form, is a URL-safe string that contains an encoded JSON object. JWTs are an open industry standard that are fully described in RFC 7519, which contains an immense amount of detail, specifically regarding how JWT claims function to ensure the security of a generated token. Feel free to peruse the full RFC specifications at your leisure.

Let’s look at an example token:

eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjF9.uKe3CzH_g6oHxlFstQ1BL_Q8_zJKPyJ0dUvZkJsRKBg
Notice that the token contains two periods (.) separating the three pieces of the outputted token, those three pieces being the following:

header
The header contains information that identifies what the hashing algorithm is, so that it can be used to properly decrypt and validate the signature.
payload
This contains the information you wish to send with your JWT. Note that the payload is not secure and can be decoded without a secret key. JWTs are not meant to send sensitive information, such as passwords or credit card numbers.
signature
The signature combines the encoded header and the payload with a secret key and securely encodes it using the hashing algorithm defined in the header — for example, HMAC with SHA-256.
To summarize, each time you generate a token:

the header will remain constant (assuming you do not change the hashing algorithm);
the payload will remain constant when the payload to encode is the same;
the signature will encrypt these two pieces of information based on the hashing algorithm with a secret key. This means that if you do not generate a unique secret key or change the payload, then the signature will also remain the same.
