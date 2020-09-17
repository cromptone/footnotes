from flask import Flask, request
app = Flask(__name__)

@app.route('/parse', methods=['POST'])
def hello_world():
    text = request.get_json()
    return "--> " + str(text)