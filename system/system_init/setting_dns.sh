function main(){
    sed -i '$a\nameserver 219.141.136.10                       #电信' /etc/resolv.conf
    sed -i '$a\nameserver 114.144.114.114                      #国内域名服务器' /etc/resolv.conf
}

main