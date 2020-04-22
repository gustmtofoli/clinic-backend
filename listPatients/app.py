import json
from aws.rds.PatientPostgres import PatientPostgres
from flask import Flask, request, Response
from flask_restplus import Resource, Api
from flask import jsonify
from flask_api import status

app = Flask(__name__)
api = Api(app)

api = api.namespace('', description="List Patients")

db = PatientPostgres()
_, _ = db.connect()

@app.route("/", endpoint='listPatients')
class ListPatients(Resource):
    def get(self, cpf):

        # if not validations.isValidCpf(cpf):
        #     return {'code': '1001', "message": 'Invalid CPF'}, status.HTTP_412_PRECONDITION_FAILED

        patients = db.selectAll()

        return json.dumps(patients.__dict__)


if __name__ == '__main__':
    app.run(port='5001', host='0.0.0.0')
