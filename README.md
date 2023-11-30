# DAI lab: SMTP
***

## Description

In this GitHub you will find a program for sending mail.

Emails are sent using the SMTP protocol.
We have a list of victims.
A mail is sent to a group.
The first victim in a group is the sender, the others are the recipients.

To use this code, you need an SMTP server.
You can find [here](#mock-server-setup) how to set up a local SMTP Server.

Prerequisites : docker, maven and java.

## Mock server setup
For using this code you need an SMTP server.

In this project we use a docker image name [maildev](https://github.com/maildev/maildev)

To run / download the image you need to run this command.
``` 
docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

To connect to the web interface you need to use this URL :
```
http://localhost:1080
```

To connect to the SMTP server :
```
http://localhost:1025
```

## Tool configuration and usage
### Configure recipients and senders
To configure the recipients and the senders, you need to fill in a JSON file as shown in the example below :
```
  {
    "email": "test@hotmail.com"
  }
```
### Configure the prank mail contents
To configure the prank mail contents, you need to fill in a JSON file as shown in the example below :
```
  {
    "subject": "Test subject",
    "body": "Test body."
  }
```

### Tool configuration 
Firstly, you need to package the application with maven :
```
mvn clean compile assembly:single
```

Then, you can run it with this command :
```
java -jar ./target/dai-lab-smtp-1.0.jar <arguments>
```

The arguments are listed below :
- "-g"            --> number of groups (***mandatory***)
- "-v"            --> path to the victim.json file (***mandatory***)
- "-m"            --> path to the message.json file (***mandatory***)
- "--server-host" --> server SMTP IP (not mandatory, by default : localhost)
- "--server-port" --> server Port (not mandatory, by default : 25)

Full example :
```
java -jar ./target/dai-lab-smtp-1.0.jar -g 3 -v ./src/main/java/ch/heig/dai/lab/smtp/data/victim.json -m ./src/main/java/ch/heig/dai/lab/smtp/data/message.json --server-port 1025
```

## Technical details
In this application, we use these specific libraries :
- jackson-databind --> to read the JSON file
- logback-classic --> simple logging facade for Java applications
- lombok --> to auto generate the getter and setter and also the logger in our classes.
- jcommander --> to parse the CLI arguments

### Class diagram

### Client dialog
