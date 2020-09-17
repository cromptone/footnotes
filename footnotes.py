from flask import Flask, request
app = Flask(__name__)

@app.route('/parse/<encoded_string>', methods=['GET'])
def hello_world(encoded_string):
    text = request.get_json()
    return "--> " + str(encoded_string)
