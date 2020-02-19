import json
from aws.rds.PatientPostgres import PatientPostgres

db = PatientPostgres()
cursor, connection = db.connect()


def lambda_handler(event, context):
    try:
        patientId = event.patient.id
        patients = db.delete(patientId)
        print(patients)

        return {
            'statusCode': 200,
            'body': json.dumps('Hello from Lambda!')
        }
    except (Exception, db.Error) as error:
        return {
            'statusCode': 500,
            'message': json.dumps('Unknown error in deletePatients: ' + error)
        }
