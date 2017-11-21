SOFT_HOME=~/soft
SHELL_HOME=~/shell
SERVICE_HOME=~/service
PROJECT_HOME=~/project
DATA_HOME=~/data
LOGS_HOME=~/logs
FILE_HOME=~/soft/file

function unZip(){
    cd ${SOFT_HOME}
    tar -zxvf mongodb-linux-x86_64-rhel70-3.4.10.tgz
    mv mongodb-linux-x86_64-rhel70-3.4.10 ${SERVICE_HOME}/mongodb-3.4.10
    mkdir ${SERVICE_HOME}/mongodb-3.4.10/conf
    mkdir $LOGS_HOME/mongodb
    mkdir $DATA_HOME/mongodb
}
function main(){
    unZip
}

main