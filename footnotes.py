from flask import Flask, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/parse/<encoded_string>', methods=['GET'])
def hello_world(encoded_string):
    text = request.get_json()
    return "--> " + str(encoded_string)
