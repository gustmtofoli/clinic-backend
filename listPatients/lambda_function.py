import json
from aws.rds.PatientPostgres import PatientPostgres

db = PatientPostgres()
cursor, connection = db.connect()


def lambda_handler(event, context):
    try:
        patients = db.selectAll()
        print(patients)

        return {
            'statusCode': 200,
            'body': json.dumps('Hello from Lambda!')
        }
    except (Exception, db.Error) as error:
        return {
            'statusCode': 500,
            'message': json.dumps('Unknown error in listPatients: ' + error)
        }
