$jarPath = Join-Path $PSScriptRoot "target\spring-learn-0.0.1-SNAPSHOT.jar"

if (-not (Test-Path -LiteralPath $jarPath)) {
    Write-Host "Jar not found. Run 'mvn clean package' first." -ForegroundColor Red
    exit 1
}

$output = & java -jar $jarPath 2>&1

function Show-Section {
    param(
        [string]$Title,
        [string[]]$Patterns
    )

    Write-Host ""
    Write-Host "===== $Title =====" -ForegroundColor Cyan
    foreach ($line in $output) {
        foreach ($pattern in $Patterns) {
            if ($line -match $pattern) {
                Write-Host $line
                break
            }
        }
    }
}

Show-Section -Title "Hands-on 1" -Patterns @(
    "SpringLearnApplication main\(\) started",
    "Tomcat initialized with port 8083",
    "Started SpringLearnApplication"
)

Show-Section -Title "Hands-on 2" -Patterns @(
    "displayDate\|START",
    "displayDate\|Parsed Date",
    "displayDate\|END"
)

Show-Section -Title "Hands-on 3" -Patterns @(
    "displayDate\|START",
    "displayDate\|Parsed Date",
    "displayDate\|END"
)

Show-Section -Title "Hands-on 4" -Patterns @(
    "displayCountry\|START",
    "Inside Country Constructor\.",
    "Inside setCode\(\)\.",
    "Inside setName\(\)\.",
    "displayCountry\|Country :",
    "displayCountry\|Singleton scope check",
    "displayCountry\|END"
)

Show-Section -Title "Hands-on 5" -Patterns @(
    "displayPrototypeCountry\|START",
    "displayPrototypeCountry\|Prototype Country 1",
    "displayPrototypeCountry\|Prototype Country 2",
    "displayPrototypeCountry\|Prototype scope check",
    "displayPrototypeCountry\|END"
)

Show-Section -Title "Hands-on 6" -Patterns @(
    "displayCountries\|START",
    "displayCountries\|Countries :",
    "displayCountries\|END"
)

Show-Section -Title "Final" -Patterns @(
    "SpringLearnApplication main\(\) completed"
)
