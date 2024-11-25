<template>



    <div class="sobrenos">
        <div class="card">
            <h3>Vinicius Queiroz Lima</h3>
            <p>Idade: 22</p>
            <p>Qualidade de Software QA | Front-End | AZ-900 | Python | Java SpringBoot | MySQL </p>
            <p>Sobre: Sou estudante de Análise e Desenvolvimento de Sistemas, e atualmente estou estagiando em Quality
                Assurance (QA).</p>
            <div class="end-icons">
                <div class="icons">
                    <a href="https://www.linkedin.com/in/viniciusqueiroztlima/" target="_blank">
                        <Linkedin />
                    </a>
                    <a href="https://github.com/Vinicinz" target="_blank">
                        <Github />
                    </a>
                </div>
            </div>
        </div>

        <div class="card">
            <h3>Sander Hideki Ferreira</h3>
            <p>Idade: 19</p>
            <p>Desenvolvedor Software & Database Developer | JAVA | MySQL | SpringBoot | Docker | Azure Fundamentals</p>
            <p>Sobre: Formado em Técnico em Informática pelo SENAC Tatuapé. conhecimento em linguagem Java, MySQL,
                SpringBoot
            </p>
            <div class="end-icons">
                <div class="icons">
                    <a href="https://www.linkedin.com/in/hideki-ferreira/" target="_blank">
                        <Linkedin />
                    </a>
                    <a href="https://github.com/hidekibtw" target="_blank">
                        <Github />
                    </a>
                </div>
            </div>
        </div>
        <div class="card">
            <h3>Igor Ikeda</h3>
            <p>Idade: 25</p>
            <p>Análise e Desenvolvimento de Sistemas // AZ - 900 // PowerBi // Python // Java</p>
            <p>Sobre: Estudante de Análise e Desenvolvimento de Sistemas
            </p>
            <div class="end-icons">
                <div class="icons">
                    <a href="https://www.linkedin.com/in/igor-ikeda-861173287/" target="_blank">
                        <Linkedin />
                    </a>
                    <a href="https://github.com/IkeD99" target="_blank">
                        <Github />
                    </a>
                </div>
            </div>
        </div>

        <div class="card">
            <h3>Monique Silva</h3>
            <p>Idade: 19</p>
            <p>Estudante de Sistemas da Informação e Desenvolvedora Front-End </p>
            <p>Sobre: 
            </p>
            <div class="end-icons">
                <div class="icons">
                    <a href="https://www.linkedin.com/in/hideki-ferreira/" target="_blank">
                        <Linkedin />
                    </a>
                    <a href="https://github.com/hidekibtw" target="_blank">
                        <Github />
                    </a>
                </div>
            </div>
        </div>

        <div class="card">
            <h3>Dheric Oliveria</h3>
            <p>Idade: 19</p>
            <p>Aluno na Universidade Anhembi Morumbi</p>
            <p>Sobre: Estudante de Desenvolvimento de Sistemas
            </p>
            <div class="end-icons">
                <div class="icons">
                    <a href="https://www.linkedin.com/in/dheric-oliveira-b76808298/" target="_blank">
                        <Linkedin />
                    </a>
                    <a href="" target="_blank">
                        <Github />
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import Github from '@/components/icons/github.vue';
import Linkedin from '@/components/icons/linkedin.vue';

export default {

    components: {
        Github, Linkedin
    }

}
</script>

<style>
.sobrenos {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    padding: 20px;
}

.linkedin {
    display: block;
    width: 25px;
    height: 25px;
}

.card {
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
    width: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: left;
}

.card h3 {
    margin-bottom: 10px;
    font-size: 18px;
}

.card p {
    margin-bottom: 5px;
    font-size: 14px;
    color: #555;
}

.end-icons {
    margin-top: auto;
    width: 100%;
    display: flex;
    justify-content: center;
}

.icons {
    display: flex;
    gap: 10px;
}



.card .icons svg:hover {
    fill: #0073b1;
    /* LinkedIn blue */
}

.card .icons svg.github:hover {
    fill: #000;
    /* GitHub black */
}
</style>