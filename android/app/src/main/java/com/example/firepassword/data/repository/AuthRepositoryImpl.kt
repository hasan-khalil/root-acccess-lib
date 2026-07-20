package com.example.firepassword.data.repository

import com.example.firepassword.data.model.User
import com.example.firepassword.data.remote.FirebaseSource
import com.example.firepassword.utils.Resource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseSource: FirebaseSource
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Resource<Unit> {

        return try {

            val result = firebaseSource.auth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val uid = result.user!!.uid

            val user = User(
                uid = uid,
                username = username,
                email = email
            )

            firebaseSource.firestore
                .collection("users")
                .document(uid)
                .set(user)
                .await()

            Resource.Success(Unit)

        } catch (e: FirebaseAuthInvalidCredentialsException) {

            Resource.Error("البريد الإلكتروني أو كلمة المرور غير صحيحة")

        }
        catch (e: FirebaseAuthInvalidUserException) {

            Resource.Error("الحساب غير موجود")

        }
        catch (e: FirebaseNetworkException) {

            Resource.Error("تحقق من اتصال الإنترنت")

        }
        catch (e: Exception) {

            Resource.Error("حدث خطأ غير متوقع")

        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit> {

        return try {

            firebaseSource.auth
                .signInWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            Resource.Success(Unit)

        } catch (e: FirebaseAuthInvalidCredentialsException) {

            Resource.Error("البريد الإلكتروني أو كلمة المرور غير صحيحة")

        }
        catch (e: FirebaseAuthInvalidUserException) {

            Resource.Error("الحساب غير موجود")

        }
        catch (e: FirebaseNetworkException) {

            Resource.Error("تحقق من اتصال الإنترنت")

        }
        catch (e: Exception) {

            Resource.Error("حدث خطأ غير متوقع")

        }
    }

    override fun logout() {

        firebaseSource.auth.signOut()

    }
}