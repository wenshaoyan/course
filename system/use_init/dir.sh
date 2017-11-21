function check(){
    if [ ! -d "/data" ] ; then  
        exit 1  
    fi
}

function createDir(){
    cd /data
    mkdir data log project service shell soft
}

function main(){
    check
    createDir
}
main 