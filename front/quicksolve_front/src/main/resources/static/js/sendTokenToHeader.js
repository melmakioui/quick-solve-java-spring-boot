function addTokenHeader(){
    const myHeaders = new Headers();
    myHeaders.append("Authorization", localStorage.getItem("token"));
}
