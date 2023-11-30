# DAI lab: SMTP
***

## Description

In this github you will find a program for sending mail.
Emails are sent using the SMTP protocol
We have a list of victims.
A mail is sent to a group.
The first victim in a group is the sender, the others are the recipients.
To use this code, you need an SMTP server.
You can find [here](#mock-server-setup) how we set up a local SMTP Server.


## Mock server setup
To setup the mock server used by this client.

For using this code you need an SMTP server.
In this project we use a docker image name [maildev](https://github.com/maildev/maildev)

To run / download the image you need to run this command.
``` 
    $ docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

To connect to the web interface you need to use this URL :
```
    localhost:1080
```

To connect to the SMTP server :
```
    localhost:1025
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
To send the prank campaign, you need to specify these arguments :
- "-g"            --> number of groups
- "-v"            --> path to the victim.json file
- "-m"            --> path to the message.json file
- "--server-host" --> server SMTP IP (not mandatory, by default : localhost)
- "--server-port" --> server Port (not mandatory, by default : 25)

Example :
```
-g 3 -v .src/main/java/ch/heig/dai/lab/smtp/data/victim.json -m .src/main/java/ch/heig/dai/lab/smtp/data/message.json --server-host localhost --server-port 1025
```

## Technical details
In this application, we use these specific librairies :
- jackson-databind --> to read the JSON file
- logback-classic --> simple logging facade for Java applications
- lombok --> to auto generate the getter and setter and also the logger in our classes.
- jcommander --> to parse the CLI arguments

### Class diagram
