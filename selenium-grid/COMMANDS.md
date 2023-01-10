Command to start Hub
java -jar selenium-server-4.1.2.jar hub
Command to start Node
This node will register:

Chrome
Firefox
Safari
Note: IF you are on Windows OS, please remove part related to safari and add for Edge

java -jar -Dwebdriver.<type>.<name>s path/to/selenium/server.jar node --config /path/to/nodeConfig.json
java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.1.2.jar node --config node_config.json

NOTE: on Windows need to specify extension like gecko.exe, chrome.exe
ex:
java -jar -Dwebdriver.chrome.driver="D:\aTuAutomation\selenium-k8-webauto\selenium-grid\chromedriver.exe" -Dwebdriver.gecko.driver="D:\aTuAutomation\selenium-k8-webauto\selenium-grid\geckodriver.exe " "D:\aTuAutomation\selenium-k8-webauto\selenium-grid\selenium-server-4.4.0.jar" node --config "D:\aTuAutomation\selenium-k8-webauto\selenium-grid\node_config.json"
Next
In second stage, we will learn about how to set up using Docker