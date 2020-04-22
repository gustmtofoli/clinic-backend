FROM centos:7

#RUN yum update
RUN yum -y install --setopt=tsflags=nodocs python3-pip python3-dev
RUN yum -y update && yum clean all

WORKDIR /app

RUN pip3 install flask
RUN pip3 install flask-rest
RUN pip3 install Werkzeug==0.16.0
RUN pip3 install flask-restplus
RUN pip3 install validate_docbr
RUN pip3 install flask-api

COPY . /app

ENTRYPOINT [ "python3" ]

CMD [ "app.py" ]

EXPOSE 5001