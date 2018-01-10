SOFT_HOME=/data/soft
DOWNLOAD_URL=https://openresty.org/download/openresty-1.13.6.1.tar.gz
PREFIX=/data/data/openresty
function yumInstall(){
    sudo yum -y install readline-devel pcre-devel openssl-devel gcc
}

function download(){
    cd $SOFT_HOME
    wget $DOWNLOAD_URL
}
function tarFile(){
    tar -zxf openresty-1.13.6.1.tar.gz
    
}
function install(){
    mkdir -p $PREFIX
    cd openresty-1.13.6.1

    ./configure --prefix=$PREFIX
    make
    make install
}
function main(){
    yumInstall
    download
    tarFile
    install
}

main