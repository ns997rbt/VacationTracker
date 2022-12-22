package com.example.vacationtracker.service

import com.example.vacationtracker.exceptions.BadRequestException
import com.example.vacationtracker.exceptions.CsvImportException
import com.example.vacationtracker.model.User
import com.example.vacationtracker.model.Vacation
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@Service
class AdminService {
 // error msg moze u enum da bude modularno
    @Autowired
    lateinit var userService: UserService


    fun uploadEmployee(file: MultipartFile) {

        throwIfEmptyFile(file)
        var parser: CSVParser? = null

        try {
            var reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            parser = CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
            )

            for (csvRecord in parser) {
                val user: User = User(email = csvRecord.get(0), password = csvRecord.get(1))
                userService.uploadUser(user)
            }
            closeFileReader(reader)
        } catch (ex: IOException) {
            throw CsvImportException("uploadEmployee parser failure")
        }

    }

    fun uploadTotal(file: MultipartFile) {

        throwIfEmptyFile(file)
        var parser: CSVParser? = null

        try {
            var reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            var year: String = reader.readLine()
            parser = CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
            )

            for (csvRecord in parser) {
                //proveri dal je null i skipuj
                val user: User = userService.findByEmail(csvRecord.get(0))
                val vacationDaysTotal: MutableMap<String, Int>? = user.vacationDaysTotal
                // fali dal je used <= toatal + promena
                user.vacationDaysTotal?.set(year, csvRecord.get(1).toInt())
                userService.uploadUser(user)
            }
            closeFileReader(reader)
        } catch (ex: IOException) {
            throw CsvImportException("uploadTotal parser failure")
        }
    }

    fun uploadUsed(file: MultipartFile) {

            throwIfEmptyFile(file)
            var parser: CSVParser? = null

            try {
                var reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
                parser = CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                )

                for (csvRecord in parser!!) {
                    val user: User = userService.findByEmail(csvRecord.get(0))

                    val vacation: Vacation = Vacation(startDate = csvRecord.get(1), endDate = csvRecord.get(2), employee = user)

                    user.vacations?.add(vacation)
                }
                closeFileReader(reader)
            } catch (ex: IOException) {
                throw CsvImportException("uploadTotal parser failure")
            }

    }

    private fun throwIfEmptyFile(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException("Empty file")
    }

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during CSV import")
        }
    }

}