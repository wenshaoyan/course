function addUser(){
    useradd wenshao
    echo "314106" | passwd --stdin "wenshao"

}
function setUser(){
    sed -i '$a\wenshao ALL=(ALL) NOPASSWD: ALL' /etc/sudoers
}
function main(){
    addUser
    setUser
    su - wenshao
}

  
main
