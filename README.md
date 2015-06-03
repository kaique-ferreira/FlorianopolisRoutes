# FlorianopolisRoutes

Projeto simples do android studio, basta importar e rodar

Para que a api do maps funcione após buildar o aplicativo, é necessário seguir os passos dessa página:

https://developers.google.com/maps/documentation/android/start

Em resumo, é necessário obter a chave de assinatura da app no computador que está sendo buildado. Isso é feito pelo comando keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

Feito isso deve-se acessar https://console.developers.google.com , criar um projeto, habilitar a api de maps , ir em credentials, criar uma chave nova usando-se o sha1 obtido pelo comando keystore. Feito isso deve-se atualizar a entrada google_maps_key no arquivo google_maps_api.xml com a chave obtida
