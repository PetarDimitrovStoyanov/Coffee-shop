fetch('http://localhost:8080/api/statistic')
    .then(response => response.json())
    .then(data => {
        data.forEach(
            resource => {
                let date = resource.localDateTime.substring(0, 16).replace("T", " / ");
                let id = resource.id
                const span = document.querySelector("#statistic-tBody");
                const html = `
                <tr class="template-statistic">
                   <th class="number-statistic" scope="row">${id}</th>
                   <td class="ip-statistic">${resource.ipAddress}</td>
                   <td class="date-statistic">${date}</td>
               </tr>`

                span.insertAdjacentHTML("beforeend", html)
            });
    });