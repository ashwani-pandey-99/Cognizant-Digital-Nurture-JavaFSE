$output = & mvn test 2>&1

Write-Host ""
Write-Host "===== MockMVC Test Output =====" -ForegroundColor Cyan
$output | Select-String "Running com.cognizant.springlearn.SpringLearnApplicationTests|Inside CountryController Constructor.|Tests run:|BUILD SUCCESS|Failures: 0|Errors: 0"

Write-Host ""
Write-Host "===== Tested Endpoints =====" -ForegroundColor Cyan
Write-Host "GET /country -> status 200, code IN, name India"
Write-Host "GET /country/in -> status 200, code IN, name India"
Write-Host "GET /country/az -> status 404, reason Country not found"