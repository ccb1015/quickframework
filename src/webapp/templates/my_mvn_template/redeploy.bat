${codeDisk}
cd ${codePath}

mvn ${webserver.serverType}<#if webserver.version != 6>${webserver.version}</#if>:redeploy
