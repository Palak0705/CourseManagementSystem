document.getElementById("signup-form").addEventListener("submit", async function (e) {
  e.preventDefault(); // Prevent the default form submission

  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:5000/api/auth/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, email, password }),
    });

    const data = await response.json();
    if (response.ok) {
      // Show success message
      alert("Signup successful! Redirecting to Login Page...");
      
      // Ensure the form is not reset and redirect after a slight delay
      setTimeout(() => {
        window.location.href = "login.html"; // Redirect to login page
      }, 500); // Delay redirect to allow the success message to be shown
    } else {
      alert(data.message);
    }
  } catch (error) {
    console.error("Error:", error);
    alert("Something went wrong. Please try again.");
  }
});
