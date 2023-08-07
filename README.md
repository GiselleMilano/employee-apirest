# employee-apirest
The application.example.properties is a example of the properties that you should set in your application.properties file.
To get started you must first create the database "employee_db".

## Create Job

Endpoint: `/api/jobs/add-job`

Method: `POST`

Request:

```jsonc
{
    "name": "Doctor",
    "salary": 200
}
```

Response:

```jsonc
{
    "id": 1, //id inserted or null on error case
    "success": true //true if the record was inserted or false on error case
}
```

```jsonc
{
    "id": null,
    "success": false
}
```

## Create Gender

Endpoint: `/api/genders/add-gender`

Method: `POST`

Request:

```jsonc
{
    "name": "Male"
}
```

Response:

```jsonc
{
    "id": 1, //id inserted or null on error case
    "success": true //true if the record was inserted or false on error case
}
```

```jsonc
{
    "id": null,
    "success": false
}
```
## Create Employee

Endpoint: `/api/employees/add-employee`

Method: `POST`

Request:

```jsonc
{
    "gender_id": 1, //genders catalog id
    "job_id": 1, //jobs catalog id
    "name": "John", //employee's name
    "last_name": "Doe", //employee's last name
    "birthdate": "2000-05-03" //employee's date of birth
}
```

Response:

```jsonc
{
    "id": 1, //id inserted or null on error case
    "success": true //true if the record was inserted or false on error case
}
```

```jsonc
{
    "id": null,
    "success": false
}
```

## Create Employee Worked Hours

Endpoint: `/api/employee-worked-hours/add-worked-hours`

Method: `POST`

Request:

```jsonc
{
    "employee_id": 1, //employees catalog id
    "worked_hours": 10, //employee worked hours
    "worked_date": "2023-08-06" //employee worked date
}
```

Response:

```jsonc
{
    "id": 1, //id inserted or null on error case
    "success": true //true if the record was inserted or false on error case
}
```

```jsonc
{
    "id": null,
    "success": false
}
```

## Get Employees by Job

Endpoint: `/api/employees/by-job`

Method: `POST`

Request:

```jsonc
{
    "job_id": 1
}
```

Response:

```jsonc
{
    "employees": [ //array of employees
        {
            "id": 1,
            "name": "Roberto",
            "last_name": "Gomez",
            "birthdate": "2000-05-03",
            "gender": {
                "id": 1,
                "name": "Male"
            },
            "job": {
                "id": 1,
                "name": "Doctor",
                "salary": 200.0
            },
            "gender_id": 1,
            "job_id": 1
        },
        ...
    ],
    "success": true //true if the data was successfully obtained, false on error case
}
```

```jsonc
{
    "employees": [],
    "success": false //false if no employees were found or there is an error
}
```

## Get employee's total worked hours

Endpoint: `/api/employee-worked-hours/total-worked-hours`

Method: `POST`

Request:

```jsonc
{
    "employee_id": 1, //employees catalog id
    "start_date": "2023-08-01",
    "end_date": "2023-08-10"
}
```
Response:

```jsonc
{
    "total_worked_hours": 10, //employee's total worked hours or null on error case
    "success": true //true if the data was successfully obtained, false on error case
}
```

```jsonc
{
    "total_worked_hours": null,
    "success": false
}
```

## Get employee's total payment in a date range

Endpoint: `/api/jobs/total-payment`

Method: `POST`

Request:

```jsonc
{
    "employee_id": 1, //employees catalog id
    "start_date": "2023-08-01",
    "end_date": "2023-08-10"
}
```
Response:

```jsonc
{
    "payment": 2000.0, //amount paid to the employee or null in case of error
    "success": true //true if the data was successfully obtained, false on error case
}
```

```jsonc
{
    "payment": null,
    "success": false
}
```

> Nota: En este endpoint el cálculo final se realiza operando el salario del Job multiplicado por el total de horas trabajadas en un rango de fecha determinado ya que no hay suficiente información.
