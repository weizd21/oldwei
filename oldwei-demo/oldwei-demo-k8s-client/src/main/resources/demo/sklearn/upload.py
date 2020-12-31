from minio import Minio
from minio.error import ResponseError

minio = Minio('152.136.218.90:9000',
                  access_key='admin',
                  secret_key='123@abc.com',
                  secure=False)

try:
    minio.fput_object('models', 'model.joblib', 'model.joblib')
except ResponseError as err:
    print(err)

