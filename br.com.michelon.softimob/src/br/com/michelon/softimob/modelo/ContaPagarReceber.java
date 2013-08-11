package br.com.michelon.softimob.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.eclipse.ui.IEditorInput;

import br.com.michelon.softimob.aplicacao.exception.ParametroNaoInformadoException;
import br.com.michelon.softimob.aplicacao.service.ContaPagarReceberService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.tela.view.PgtoRecContaView;

@Entity
@MappedSuperclass
public class ContaPagarReceber implements Serializable, Pendencia{

	private static final long serialVersionUID = 1L;

	public static final int PAGAR = 1;
	public static final int RECEBER = 2;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O valor da conta não pode ser vazio.")
	@Column(precision = 14, scale = 2, nullable = false)
	private BigDecimal valor;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valorPagoParcial = BigDecimal.ZERO;
	
	@Column(precision = 14, scale = 2)
	private BigDecimal valorJurosDesconto = BigDecimal.ZERO;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataConta = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento = new Date();
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	@NotNull(message = "O tipo da conta não pode ser vazia.")
	@ManyToOne(optional = false)
	private OrigemConta origem;
	
	@NotNull(message = "Selecione o tipo da conta.")
	@Column(length = 1, nullable = false)
	private Integer tipo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private MovimentacaoContabil movimentacao;

	@Column
	private String observacoes;
	
	@OneToOne
	private ContaPagarReceber contaPai;
	
	public ContaPagarReceber(FinalizacaoChamadoReforma fin) throws ParametroNaoInformadoException{
		OrigemConta tipoContaPrestacaoServico = ParametrosEmpresa.getInstance().getTipoContaReforma();
		if(tipoContaPrestacaoServico == null)
			throw new ParametroNaoInformadoException("A origem da conta referente ao chamado de reforma deve ser informado em Parâmetros da Empresa");
		
		setOrigem(tipoContaPrestacaoServico);
		setTipo(ContaPagarReceber.PAGAR);
	}
	
	public ContaPagarReceber(){
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	@Override
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorPagoParcial() {
		return valorPagoParcial;
	}

	public void setValorPagoParcial(BigDecimal valorPagoParcial) {
		this.valorPagoParcial = valorPagoParcial;
	}

	public BigDecimal getValorJurosDesconto() {
		return valorJurosDesconto;
	}

	public void setValorJurosDesconto(BigDecimal valorJurosDesconto) {
		this.valorJurosDesconto = valorJurosDesconto;
	}

	@Override
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public OrigemConta getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemConta origem) {
		this.origem = origem;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public MovimentacaoContabil getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacaoContabil movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Date getDataConta() {
		return dataConta;
	}

	public void setDataConta(Date dataConta) {
		this.dataConta = dataConta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void efetuarBaixa(MovimentacaoContabil movimentacao2) {
		// TODO Auto-generated method stub
	}

	@Override
	public Date getDataGeracao() {
		return dataConta;
	}

	@Override
	public Date getDataFechamento() {
		return dataPagamento;
	}

	@Override
	public String getDescricao() {
		return "Conta " + getTipoExtenso() + " originada de "+ getTipoExtenso();
	}

	@Override
	public String getIdEditor() {
		return PgtoRecContaView.ID;
	}

	@Override
	public IEditorInput getEditorInput() {
		return null;
	}

	public BigDecimal getValorPagoParcialTratado(){
		if(valorPagoParcial == null || valorPagoParcial.signum() == 0 || valorPagoParcial.compareTo(valor) == 0)
			return BigDecimal.ZERO;
		return valorPagoParcial;
	}
	
	public BigDecimal getValorParcialOuValorCheio(){
		return getValorPagoParcialTratado().signum() == 0 ? getValor() : getValorPagoParcialTratado();
	}
	
	public BigDecimal getValorJurDescTratado(){
		return valorJurosDesconto == null ? BigDecimal.ZERO : valorJurosDesconto;
	}
	
	public boolean isApagar(){
		return tipo == PAGAR;
	}
	
	public boolean isAReceber(){
		return tipo == RECEBER;
	}
	
	public String getTipoExtenso(){
		return isApagar() ? "A Pagar" : isAReceber() ? "A Receber" : "";
	}
	
	public BigDecimal getValorMovimentacao(){
		return getValorParcialOuValorCheio().add(getValorJurDescTratado());
	}
	
	public BigDecimal getValorDebito(){
		if( getValorJurDescTratado().signum() == 0 )
			return getValorParcialOuValorCheio();
		else{
			return isAReceber() ? getValorParcialOuValorCheio().add(getValorJurDescTratado()) : getValorParcialOuValorCheio();
		}
	}
	
	public BigDecimal getValorCredito(){
		if( getValorJurDescTratado().signum() == 0 )
			return getValorParcialOuValorCheio();
		else{
			return isApagar() ? getValorParcialOuValorCheio().add(getValorJurDescTratado()) : getValorParcialOuValorCheio();
		}
	}

	public ContaPagarReceber getContaPai() {
		return contaPai;
	}

	public void setContaPai(ContaPagarReceber contaPai) {
		this.contaPai = contaPai;
	}
	
	private transient static ContaPagarReceberService c;
	
	@Override
	public GenericService<?> getService() {
		if(c == null)
			c = new ContaPagarReceberService();
		return c;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagarReceber other = (ContaPagarReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
