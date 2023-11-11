//

const fileSuffix = () => {
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let day = today.getDate();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
   return year + "" + month + "" + day;
}
const exportExcel = {
    download(fileName, url) {
        fileName = fileName || "导出报表";
        let arr = url.split(".");
        fileName += '-' + fileSuffix();
        console.log(fileName)
        const linkNode = document.createElement("a");
        linkNode.download = fileName;
        linkNode.style.display = "none";
        if (process.env.VUE_APP_TEM_PATH) {
            linkNode.href = process.env.VUE_APP_TEM_PATH + url;
        } else {
            linkNode.href = url;
        }
        document.body.appendChild(linkNode);
        linkNode.click();
        URL.revokeObjectURL(linkNode.href);
        document.body.removeChild(linkNode);
    }
}

export default exportExcel