package com.example.vacationtracker.service

import com.example.vacationtracker.errorMessages.ErrorMessage
import com.example.vacationtracker.exceptions.BadRequestException
import com.example.vacationtracker.exceptions.CsvImportException
import com.example.vacationtracker.model.User
import com.example.vacationtracker.model.Vacation
import com.example.vacationtracker.repo.VacationRepo
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
 // error msg moze u enum da bude modularno - done
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var dateService: DateService
    @Autowired
    lateinit var vacationRepo: VacationRepo



    fun uploadEmployee(file: MultipartFile) {

        throwIfEmptyFile(file)
        var parser: CSVParser? = null
// fix ako fali polje
        try {
            val reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            var year: String = reader.readLine().split(",")[1]
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
            throw CsvImportException(ErrorMessage.UPLOADEMPLOYEEPARSER.msg)
        }

    }

    fun uploadTotal(file: MultipartFile) {

        throwIfEmptyFile(file)
        var parser: CSVParser? = null

        try {
            val reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
            val year: String = reader.readLine().split(",")[1]
            parser = CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
            )

            for (csvRecord in parser) {
                //proveri dal je null i skipuj
                val user: User = userService.findByEmail(csvRecord.get(0))

                user.vacationDaysTotal.set(year, csvRecord.get(1).toInt())
                user.vacationDaysLeft.set(year, csvRecord.get(1).toInt())
                userService.uploadUser(user)
            }
            closeFileReader(reader)
        } catch (ex: IOException) {
            throw CsvImportException(ErrorMessage.UPLOADTOTALPARSER.msg)
        }
    }

    fun uploadUsed(file: MultipartFile) {

            throwIfEmptyFile(file)
            var parser: CSVParser? = null

            try {
                val reader = BufferedReader(InputStreamReader(file.inputStream, StandardCharsets.UTF_8))
                parser = CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                )

                for (csvRecord in parser) {
                    val user: User = userService.findByEmail(csvRecord.get(0))
                    val date1: String = csvRecord.get(1)
                    val date2: String = csvRecord.get(2)
                    // provera duplog

                    if (vacationRepo.checkDuplicates(dateService.stringToDate(date1),dateService.stringToDate(date2),user) <= 0) {
                        val vacation = Vacation(
                            startDate = dateService.stringToDate(date1),
                            endDate = dateService.stringToDate(date2),
                            duration = dateService.calculateDuration(
                                dateService.stringToDate(date1),
                                dateService.stringToDate(date2)
                            ),
                            employee = user
                        )
                        val year: Int = vacation.startDate.year

                        if (user.vacationDaysLeft[year.toString()] != null) {
                            //user.vacationDaysLeft[year.toString()] = user.vacationDaysLeft[year.toString()]!! - duration
                            //user.vacations!!.add(vacation)
                            //userService.uploadVacation(vacation)
                            if (user.vacationDaysLeft[year.toString()]!! >= vacation.duration) {
                                user.vacationDaysLeft[year.toString()] =
                                    user.vacationDaysLeft[year.toString()]!! - vacation.duration
                                user.vacations.add(vacation)
                                userService.uploadVacation(vacation)
                            }
                        }
                    }
                }
                closeFileReader(reader)
            } catch (ex: IOException) {
                throw CsvImportException(ErrorMessage.UPLOADUSEDPARSER.msg)
            }

    }

    private fun throwIfEmptyFile(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException(ErrorMessage.EMPTYFILE.msg)
    }

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException(ErrorMessage.BADCSVIMPORT.msg)
        }
    }

}